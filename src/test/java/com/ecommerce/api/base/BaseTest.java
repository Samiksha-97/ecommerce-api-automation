package com.ecommerce.api.base;

import com.ecommerce.api.config.Environment;
import com.ecommerce.api.config.RequestSpecFactory;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import org.testng.annotations.BeforeTest;

public class BaseTest {

    protected RequestSpecification requestSpec;

    @BeforeTest
    public void setup() {

        RestAssured.baseURI = Environment.BASE_URL;

        requestSpec = RequestSpecFactory.createDefaultRequestSpec();
    }
}