package com.ecommerce.api.tests.payment;

import org.testng.Assert;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import org.testng.annotations.Test;

import com.ecommerce.api.testdata.payment.PaymentTestData;
import com.ecommerce.api.utils.PaymentBusinessValidator;

@Epic("E-Commerce API")
@Feature("Payment Validation")
public class PaymentValidationTest {

	@Story("Payment Validation")
	@Severity(SeverityLevel.CRITICAL)
    @Test(groups = {"regression"})
    public void validateValidPaymentMethod() {

        PaymentBusinessValidator.validatePaymentMethod(
                PaymentTestData.VALID_PAYMENT_METHOD);
    }
    
	@Story("Payment valid amount")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verify valid payment amount")
    @Test(groups = {"regression"})
    public void validateValidPaymentAmount() {

        PaymentBusinessValidator.validateAmount(
                PaymentTestData.VALID_AMOUNT);
    }
    
	@Story("Inalid payment method")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verify invalid payment method")
    @Test(groups = {"regression"})
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