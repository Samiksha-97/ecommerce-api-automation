package com.ecommerce.api.api;

import com.ecommerce.api.endpoints.Endpoints;
import com.ecommerce.api.models.auth.LoginRequest;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuthApi extends BaseApi {

    private RequestSpecification requestSpec;

    public AuthApi(RequestSpecification requestSpec) {
    	super(requestSpec);
    }

    public Response login(LoginRequest loginRequest) {

        return request()
                .body(loginRequest)
                .post(Endpoints.LOGIN);
    }

    public Response getAuthenticatedUser(String accessToken) {

        return request()
                .header("Authorization", "Bearer " + accessToken)
                .get(Endpoints.AUTH_ME);
    }
}