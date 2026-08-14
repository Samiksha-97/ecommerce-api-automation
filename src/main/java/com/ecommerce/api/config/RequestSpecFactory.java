package com.ecommerce.api.config;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class RequestSpecFactory {

    public static RequestSpecification createDefaultRequestSpec() {

        return RestAssured
                .given()
                .accept("application/json")
                .contentType("application/json")
                .log()
                .ifValidationFails();
    }
}