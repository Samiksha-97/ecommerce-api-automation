package com.ecommerce.api.tests.auth;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ecommerce.api.api.AuthApi;
import com.ecommerce.api.base.BaseTest;
import com.ecommerce.api.models.auth.LoginRequest;
import com.ecommerce.api.testdata.auth.LoginTestData;
import com.ecommerce.api.utils.ResponseValidator;

import io.restassured.response.Response;

public class AuthApiTest extends BaseTest {

    private AuthApi authApi;
    private String accessToken;

    @BeforeClass
    public void setupAuthApi() {
        authApi = new AuthApi(requestSpec);
    }
    
    @Test(priority = 1)
    public void login() {

        LoginRequest loginRequest = new LoginRequest();

        loginRequest.setUsername(LoginTestData.USERNAME);
        loginRequest.setPassword(LoginTestData.PASSWORD);

        Response response = authApi.login(loginRequest);
        
        ResponseValidator.validateSuccessResponse(response, 200);
        response.then()
                .log().ifValidationFails()
                .body("accessToken", notNullValue());

        accessToken = response.jsonPath().getString("accessToken");
    }
    
    @Test(priority = 2, dependsOnMethods = "login")
    public void getAuthenticatedUser() {

        Response response =
                authApi.getAuthenticatedUser(accessToken);

        ResponseValidator.validateSuccessResponse(response, 200);
        response.then()
                .log().ifValidationFails()
                .body("username", equalTo(LoginTestData.USERNAME))
                .body("id", notNullValue());
    }
    
}