package com.ecommerce.api.tests.payment;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ecommerce.api.testdata.payment.PaymentTestData;
import com.ecommerce.api.utils.PaymentBusinessValidator;

public class PaymentValidationTest {

    @Test
    public void validateValidPaymentMethod() {

        PaymentBusinessValidator.validatePaymentMethod(
                PaymentTestData.VALID_PAYMENT_METHOD);
    }
    
    @Test
    public void validateValidPaymentAmount() {

        PaymentBusinessValidator.validateAmount(
                PaymentTestData.VALID_AMOUNT);
    }
    
    @Test
    public void validateInvalidPaymentMethod() {

        try {

            PaymentBusinessValidator.validatePaymentMethod(
                    PaymentTestData.INVALID_PAYMENT_METHOD);

            Assert.fail("Expected invalid payment method validation");

        } catch (IllegalArgumentException e) {

            Assert.assertEquals(
                    e.getMessage(),
                    "Invalid payment method");
        }
    }
}