package com.ecommerce.api.tests.workflow.OrderManagementWorkflowTest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Arrays;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ecommerce.api.api.AuthApi;
import com.ecommerce.api.api.OrderApi;
import com.ecommerce.api.base.BaseTest;
import com.ecommerce.api.models.auth.LoginRequest;
import com.ecommerce.api.models.orders.OrderProduct;
import com.ecommerce.api.models.orders.OrderRequest;
import com.ecommerce.api.testdata.auth.LoginTestData;
import com.ecommerce.api.testdata.order.OrderTestData;
import com.ecommerce.api.utils.OrderBusinessValidator;
import com.ecommerce.api.utils.PaymentBusinessValidator;
import com.ecommerce.api.utils.ResponseValidator;

import io.restassured.response.Response;

public class OrderManagementWorkflowTest extends BaseTest {

    private AuthApi authApi;
    private OrderApi orderApi;

    private String accessToken;

    @BeforeClass
    public void setupApis() {

        authApi = new AuthApi(requestSpec);
        orderApi = new OrderApi(requestSpec);
    }
    
    //login test
    @Test(priority = 1,groups = "smoke")
    public void login() {

        LoginRequest loginRequest = new LoginRequest();

        loginRequest.setUsername(LoginTestData.USERNAME);
        loginRequest.setPassword(LoginTestData.PASSWORD);

        Response response = authApi.login(loginRequest);

        ResponseValidator.validateStatusCode(response, 200);
        response.then()
                .body("accessToken", notNullValue());

        accessToken = response.jsonPath()
                .getString("accessToken");
    }
    
    //add authenticated user verification
    @Test(priority = 2, dependsOnMethods = "login")
    public void verifyAuthenticatedUser() {

        Response response =
                authApi.getAuthenticatedUser(accessToken);

        ResponseValidator.validateStatusCode(response, 200);
        response.then()
                .body("username",
                        equalTo(LoginTestData.USERNAME))
                .body("id", notNullValue());
    }
    
    //create order request
    private OrderRequest createOrderRequest() {

        OrderProduct product = new OrderProduct();

        product.setId(OrderTestData.PRODUCT_ID);
        product.setQuantity(OrderTestData.PRODUCT_QUANTITY);

        OrderRequest orderRequest = new OrderRequest();

        orderRequest.setUserId(OrderTestData.USER_ID);
        orderRequest.setProducts(
                Arrays.asList(product));

        return orderRequest;
    }
    
    //Add create order
    @Test(priority = 3, dependsOnMethods = "verifyAuthenticatedUser",groups = {"smoke","regression"})
    public void createOrder() {

        OrderRequest orderRequest = createOrderRequest();

        OrderBusinessValidator.validateQuantity(
                OrderTestData.PRODUCT_QUANTITY);

        Response response =
                orderApi.createOrder(orderRequest);

        ResponseValidator.validateStatusCode(response, 201);
        response.then()
                .body("id", notNullValue())
                .body("userId",
                        equalTo(OrderTestData.USER_ID))
                .body("products", notNullValue());
    }
    
    //add payment validation
    @Test(priority = 4, dependsOnMethods = "createOrder")
    public void validatePayment() {

        PaymentBusinessValidator.validatePaymentMethod("CARD");

        PaymentBusinessValidator.validateAmount(99.99);
    }
    
    //retrive created order
    @Test(priority = 5, dependsOnMethods = "createOrder")
    public void verifyCreatedOrder() {

    	Response response =
    	        orderApi.getOrderById(OrderTestData.CART_ID);

    	ResponseValidator.validateStatusCode(response, 200);
    	response.then()
                .body("id", equalTo(OrderTestData.CART_ID))
                .body("userId", notNullValue())
                .body("products", notNullValue());
    }
    
    //update the order
    @Test(priority = 6, dependsOnMethods = "verifyCreatedOrder")
    public void updateAndValidateOrder() {

        OrderProduct product = new OrderProduct();

        product.setId(OrderTestData.PRODUCT_ID);
        product.setQuantity(
                OrderTestData.UPDATED_PRODUCT_QUANTITY);

        OrderRequest orderRequest = new OrderRequest();

        orderRequest.setUserId(OrderTestData.USER_ID);
        orderRequest.setProducts(
                Arrays.asList(product));

        Response response =
                orderApi.updateOrder(
                        OrderTestData.CART_ID,
                        orderRequest);

        ResponseValidator.validateStatusCode(response, 200);
        response.then()
                .body("id",
                        equalTo(OrderTestData.CART_ID))
                .body("userId",
                        equalTo(OrderTestData.USER_ID))
                .body("products[0].id",
                        equalTo(OrderTestData.PRODUCT_ID))
                .body("products[0].quantity",
                        equalTo(OrderTestData.UPDATED_PRODUCT_QUANTITY))
                .body("totalProducts",
                        equalTo(1))
                .body("totalQuantity",
                        equalTo(OrderTestData.UPDATED_PRODUCT_QUANTITY));
    }
    
}