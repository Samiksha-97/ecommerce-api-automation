package com.ecommerce.api.utils;

public class OrderBusinessValidator {

    public static void validateQuantity(int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Product quantity must be greater than zero");
        }
    }
}