package com.example.problemsolving.test1;

import java.util.List;

public class InventoryManager {
    // Tính tổng giá trị kho
    public static double calculateTotalInventoryValue(List<Product> inventory) {
        double totalValue = 0;
        for (Product product : inventory) {
            totalValue += product.totalValue();
        }
        return totalValue;
    }

    // Tìm sản phẩm đắt nhất
    public static String findMostExpensiveProduct(List<Product> inventory) {
        Product mostExpensive = inventory.get(0);
        for (Product product : inventory) {
            if (product.getPrice() > mostExpensive.getPrice()) {
                mostExpensive = product;
            }
        }
        return mostExpensive.getName();
    }

    // Kiểm tra xem sản phẩm có tồn tại trong kho hay không
    public static boolean isProductInStock(List<Product> inventory, String productName) {
        for (Product product : inventory) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

    // Sắp xếp sản phẩm theo giá (tăng dần hoặc giảm dần)
    public static List<Product> sortProductsByPrice(List<Product> inventory, boolean descending) {
        inventory.sort((product1, product2) -> {
            if (descending) {
                return Double.compare(product2.getPrice(), product1.getPrice());
            } else {
                return Double.compare(product1.getPrice(), product2.getPrice());
            }
        });
        return inventory;
    }
}
