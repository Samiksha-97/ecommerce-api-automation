package com.ecommerce.api.utils;

public class PaymentBusinessValidator {

    public static void validatePaymentMethod(String paymentMethod) {

        if (paymentMethod == null || paymentMethod.isBlank()) {
            throw new IllegalArgumentException(
                    "Payment method is required");
        }

        if (!paymentMethod.equals("CARD")
                && !paymentMethod.equals("UPI")
                && !paymentMethod.equals("NET_BANKING")) {

            throw new IllegalArgumentException(
                    "Invalid payment method");
        }
    }

    public static void validateAmount(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Payment amount must be greater than zero");
        }
    }
}