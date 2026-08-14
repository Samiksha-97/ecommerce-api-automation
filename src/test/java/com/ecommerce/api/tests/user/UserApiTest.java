package com.ecommerce.api.tests.user;

import static org.hamcrest.Matchers.equalTo;
import com.ecommerce.api.utils.ResponseValidator;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ecommerce.api.api.UserApi;
import com.ecommerce.api.base.BaseTest;
import com.ecommerce.api.models.user.UserRequest;
import com.ecommerce.api.testdata.user.UserTestData;

import io.restassured.response.Response;

public class UserApiTest extends BaseTest {

    private UserApi userApi;

    @BeforeClass
    public void setupUserApi() {
        userApi = new UserApi(requestSpec);
    }
    
    @Test(priority = 1)
    public void getAllUsers() {

        Response response = userApi.getAllUsers();

        ResponseValidator.validateSuccessResponse(response, 200);
        response.then()
                .log().ifValidationFails()
                .body("size()", notNullValue());
    }
    
    @Test(priority = 2)
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
    
    @Test(priority = 3)
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
    
    @Test(priority = 4)
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
    
    @Test(priority = 5)
    public void deleteUser() {

        Response response =
                userApi.deleteUser(UserTestData.USER_ID);
        
        ResponseValidator.validateStatusCode(response, 200);
        response.then()
                .log().ifValidationFails();
    }
}