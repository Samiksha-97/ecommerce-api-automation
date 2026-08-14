package com.ecommerce.api.tests.negative;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ecommerce.api.api.AuthApi;
import com.ecommerce.api.base.BaseTest;
import com.ecommerce.api.utils.ResponseValidator;

import io.restassured.response.Response;

public class AuthenticationNegativeTest extends BaseTest {

    private AuthApi authApi;

    @BeforeClass
    public void setupAuthApi() {
        authApi = new AuthApi(requestSpec);
    }
    
    @Test
    public void getAuthenticatedUserWithInvalidToken() {

        Response response =
                authApi.getAuthenticatedUser("invalid-token");

        ResponseValidator.validateStatusCode(response, 401);
        response.then()
                .log().ifValidationFails()
                .body("message", equalTo("Invalid/Expired Token!"));
    }
}