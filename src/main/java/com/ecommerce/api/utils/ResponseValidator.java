package com.ecommerce.api.utils;

import io.restassured.response.Response;

public class ResponseValidator {

    private ResponseValidator() {
        // Utility class
    }

    public static void validateStatusCode(
            Response response, int expectedStatusCode) {

        response.then()
                .statusCode(expectedStatusCode);
    }

    public static void validateJsonContentType(Response response) {

        response.then()
                .contentType("application/json; charset=utf-8");
    }

    public static void validateSuccessResponse(
            Response response, int expectedStatusCode) {

        response.then()
                .statusCode(expectedStatusCode)
                .contentType("application/json; charset=utf-8");
    }
}