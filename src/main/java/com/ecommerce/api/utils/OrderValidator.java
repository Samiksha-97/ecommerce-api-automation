package com.ecommerce.api.utils;

import io.restassured.response.Response;

public class OrderValidator {

    public static void validateSuccessfulOrderResponse(Response response) {

        response.then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }
}