package com.example.problemsolving.test2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.problemsolving.R;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FindMissingNumber extends AppCompatActivity {

    private EditText inputArray;
    private Button calculateButton;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.find_missing_number_view);

        inputArray = findViewById(R.id.inputArray);
        calculateButton = findViewById(R.id.calculateButton);
        resultText = findViewById(R.id.resultText);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu người dùng nhập vào
                String input = inputArray.getText().toString().trim();
                if (input.isEmpty()) {
                    Toast.makeText(FindMissingNumber.this, "Vui lòng nhập mảng số", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Chuyển chuỗi nhập vào thành mảng số nguyên
                List<Integer> numberList = parseInputArray(input);

                // Tìm số thiếu và hiển thị kết quả
                int missingNumber = findMissingNumber(numberList);
                resultText.setText("Số thiếu là: " + missingNumber);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private List<Integer> parseInputArray(String input) {
        String[] numbers = input.split(",");
        return Arrays.stream(numbers)
                .map(String::trim)  // Loại bỏ khoảng trắng thừa
                .map(Integer::parseInt)  // Chuyển mỗi chuỗi thành số nguyên
                .collect(Collectors.toList());
    }

    private int findMissingNumber(List<Integer> numbers) {
        int n = numbers.size();
        int totalSum = (n + 1) * (n + 2) / 2;  // Tổng mong đợi từ 1 đến n+1
        int actualSum = 0;

        // Tính tổng các số trong mảng
        for (int num : numbers) {
            actualSum += num;
        }

        // Số thiếu là hiệu giữa tổng mong đợi và tổng thực tế
        return totalSum - actualSum;
    }
}