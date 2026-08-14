package com.ecommerce.api.api;

import com.ecommerce.api.endpoints.Endpoints;
import com.ecommerce.api.models.user.UserRequest;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserApi extends BaseApi{

    private RequestSpecification requestSpec;

    public UserApi(RequestSpecification requestSpec) {
    	super(requestSpec);
    }

    public Response getAllUsers() {

    	 return request()
    	            .get(Endpoints.USERS);
    }

    public Response getUserById(int userId) {

    	return request()
                .pathParam("id", userId)
                .get(Endpoints.USER_BY_ID);
    }

    public Response createUser(UserRequest userRequest) {

    	return request()
                .body(userRequest)
                .post(Endpoints.CREATE_USER);
    }

    public Response updateUser(int userId, UserRequest userRequest) {

    	return request()
                .pathParam("id", userId)
                .body(userRequest)
                .put(Endpoints.UPDATE_USER);
    }

    public Response deleteUser(int userId) {

    	return request()
                .pathParam("id", userId)
                .delete(Endpoints.DELETE_USER);
    }
}