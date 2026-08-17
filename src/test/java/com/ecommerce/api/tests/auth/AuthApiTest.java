package com.ecommerce.api.tests.auth;

import static org.hamcrest.Matchers.*;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ecommerce.api.api.AuthApi;
import com.ecommerce.api.base.BaseTest;
import com.ecommerce.api.models.auth.LoginRequest;
import com.ecommerce.api.testdata.auth.LoginTestData;
import com.ecommerce.api.utils.ResponseValidator;

import io.restassured.response.Response;

@Epic("E-Commerce API")
@Feature("Authentication")
public class AuthApiTest extends BaseTest {

    private AuthApi authApi;
    private String accessToken;

    @BeforeClass
    public void setupAuthApi() {
        authApi = new AuthApi(requestSpec);
    }
    
    @Story("User login")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify successful user login")
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
    
    @Story("User Authentication")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify successful user authentication")
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