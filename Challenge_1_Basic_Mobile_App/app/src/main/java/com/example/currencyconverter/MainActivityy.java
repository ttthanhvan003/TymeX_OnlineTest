package com.example.currencyconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.currencyconverter.api.ApiService;
import com.example.currencyconverter.model.Currency;
import com.example.currencyconverter.model.Rates;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityy extends AppCompatActivity {

    private static final String ACCESS_KEY = "9e35a3e80b19a0335c077d3fa63ea364";
    private EditText amountEditText;
    private Spinner currencySpinnerFrom, currencySpinnerTo;
    private TextView resultTextView;
    private Button convertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        amountEditText = findViewById(R.id.amountEditText);
        currencySpinnerFrom = findViewById(R.id.currencySpinnerFrom);
        currencySpinnerTo = findViewById(R.id.currencySpinnerTo);
        resultTextView = findViewById(R.id.resultTextView);
        convertButton = findViewById(R.id.convertButton);

        // Spinner setup
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currencies, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currencySpinnerFrom.setAdapter(adapter);
        currencySpinnerTo.setAdapter(adapter);

        convertButton.setOnClickListener(v -> {
            String amountStr = amountEditText.getText().toString();
            if (!amountStr.isEmpty()) {
                double amount = Double.parseDouble(amountStr);
                String fromCurrency = currencySpinnerFrom.getSelectedItem().toString();
                String toCurrency = currencySpinnerTo.getSelectedItem().toString();
                convertCurrency(amount, fromCurrency, toCurrency);
            } else {
                Toast.makeText(this, "Please enter an amount", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void convertCurrency(double amount, String fromCurrency, String toCurrency) {
        ApiService apiService = ApiService.create();
        Call<Currency> call = apiService.convert(ACCESS_KEY, fromCurrency, toCurrency);

        call.enqueue(new Callback<Currency>() {
            @Override
            public void onResponse(Call<Currency> call, Response<Currency> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Currency currency = response.body();
                    if (currency.isSuccess()) {
                        double rate = currency.getRates().getRates().get(toCurrency);
                        double result = amount * rate;
                        resultTextView.setText(String.format("Converted amount: %.2f", result));
                    } else {
                        Toast.makeText(MainActivityy.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivityy.this, "API call failed", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<Currency> call, Throwable t) {
                Toast.makeText(MainActivityy.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
