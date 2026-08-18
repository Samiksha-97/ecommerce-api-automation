package com.ecommerce.api.tests.user;

import static org.hamcrest.Matchers.equalTo;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import com.ecommerce.api.utils.ResponseValidator;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ecommerce.api.api.UserApi;
import com.ecommerce.api.base.BaseTest;
import com.ecommerce.api.models.user.UserRequest;
import com.ecommerce.api.testdata.user.UserTestData;

import io.restassured.response.Response;

@Epic("E-Commerce API")
@Feature("User Management")
public class UserApiTest extends BaseTest {

    private UserApi userApi;

    @BeforeClass(alwaysRun = true)
    public void setupUserApi() {
        userApi = new UserApi(requestSpec);
    }
    
    @Story("Get User")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify getting all users successfully")
    @Test(priority = 1,groups = {"smoke", "regression"})
    public void getAllUsers() {

        Response response = userApi.getAllUsers();

        ResponseValidator.validateSuccessResponse(response, 200);
        response.then()
                .log().ifValidationFails()
                .body("size()", notNullValue());
    }
    
    @Story("Get User By ID")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify getting user by ID successfully")
    @Test(priority = 2,groups = {"smoke", "regression"})
    public void getUserById() {

        Response response =
                userApi.getUserById(UserTestData.USER_ID);

        ResponseValidator.validateSuccessResponse(response, 200);
        response.then()
                .log().ifValidationFails()
                .body("id", equalTo(UserTestData.USER_ID))
                .body("username", notNullValue())
                .body("email", notNullValue());
    }
    
    @Story("Create User")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify creating user successfully")
    @Test(priority = 3,groups = {"regression"})
    public void createUser() {

        UserRequest userRequest = new UserRequest();

        userRequest.setUsername(UserTestData.USERNAME);
        userRequest.setEmail(UserTestData.EMAIL);
        userRequest.setPassword(UserTestData.PASSWORD);

        Response response = userApi.createUser(userRequest);
        
        ResponseValidator.validateSuccessResponse(response, 201);
        response.then()
                .log().ifValidationFails()
                .body("id", notNullValue());
    }
    
    @Story("Update User")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify updating user successfully")
    @Test(priority = 4,groups = {"regression"})
    public void updateUser() {

        UserRequest userRequest = new UserRequest();

        userRequest.setUsername(UserTestData.UPDATED_USERNAME);
        userRequest.setEmail(UserTestData.UPDATED_EMAIL);
        userRequest.setPassword(UserTestData.UPDATED_PASSWORD);

        Response response =
                userApi.updateUser(UserTestData.USER_ID, userRequest);

        ResponseValidator.validateSuccessResponse(response, 200);
        response.then()
                .log().ifValidationFails()
                .body("username", equalTo(UserTestData.UPDATED_USERNAME))
                .body("email", equalTo(UserTestData.UPDATED_EMAIL))
                .body("password", equalTo(UserTestData.UPDATED_PASSWORD));
    }
    
    @Story("Delete User")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify deleting user successfully")
    @Test(priority = 5,groups = {"regression"})
    public void deleteUser() {

        Response response =
                userApi.deleteUser(UserTestData.USER_ID);
        
        ResponseValidator.validateStatusCode(response, 200);
        response.then()
                .log().ifValidationFails();
    }
}