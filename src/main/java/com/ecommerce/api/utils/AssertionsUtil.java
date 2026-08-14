package com.ecommerce.api.utils;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import io.restassured.response.Response;

public class AssertionsUtil {

    public static void assertStatusCode(Response response, int expectedStatusCode) {

        response.then()
                .statusCode(expectedStatusCode);
    }

    public static void assertFieldEquals(
            Response response,
            String jsonPath,
            Object expectedValue) {

        response.then()
                .body(jsonPath, equalTo(expectedValue));
    }

    public static void assertFieldNotNull(
            Response response,
            String jsonPath) {

        response.then()
                .body(jsonPath, notNullValue());
    }
}