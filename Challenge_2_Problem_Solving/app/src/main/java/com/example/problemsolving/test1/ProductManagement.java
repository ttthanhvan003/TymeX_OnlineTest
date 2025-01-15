package com.example.problemsolving.test1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



import com.example.problemsolving.R;

import java.util.ArrayList;
import java.util.List;

public class ProductManagement extends AppCompatActivity {

    private TextView resultText;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.product_management_view);

        resultText = findViewById(R.id.resultText);
        calculateButton = findViewById(R.id.calculateButton);

        List<Product> inventory = new ArrayList<>();
        inventory.add(new Product("Laptop", 999.99, 5));
        inventory.add(new Product("Smartphone", 499.99, 10));
        inventory.add(new Product("Tablet", 299.99, 8));
        inventory.add(new Product("Smartwatch", 199.99, 3));

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tính toán tổng giá trị kho
                double totalValue = InventoryManager.calculateTotalInventoryValue(inventory);
                resultText.setText("Tổng giá trị kho: " + totalValue);

                // Tìm sản phẩm đắt nhất
                String mostExpensive = InventoryManager.findMostExpensiveProduct(inventory);
                resultText.append("\nSản phẩm đắt nhất: " + mostExpensive);

                // Kiểm tra sản phẩm có tồn tại trong kho không
                boolean isInStock = InventoryManager.isProductInStock(inventory, "Headphones");
                resultText.append("\n'Headphones' có trong kho? " + isInStock);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}