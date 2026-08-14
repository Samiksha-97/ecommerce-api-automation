package com.ecommerce.api.api;

import static io.restassured.RestAssured.given;

import io.restassured.specification.RequestSpecification;

public class BaseApi {

    protected RequestSpecification requestSpec;

    public BaseApi(RequestSpecification requestSpec) {
        this.requestSpec = requestSpec;
    }

    protected RequestSpecification request() {
        return given()
                .spec(requestSpec);
    }
}