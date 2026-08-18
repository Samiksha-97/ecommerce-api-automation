package com.ecommerce.api.tests.workflow;

import static org.hamcrest.Matchers.equalTo;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

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

@Epic("E-Commerce API")
@Feature("End-to-End Order Workflow")
public class OrderManagementWorkflowTest extends BaseTest {

    private AuthApi authApi;
    private OrderApi orderApi;

    private String accessToken;

    @BeforeClass(alwaysRun = true)
    public void setupApis() {

        authApi = new AuthApi(requestSpec);
        orderApi = new OrderApi(requestSpec);
    }
    
    @Story("End-to-End Order Workflow - Login user")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify user login successfully")
    //login test
    @Test(priority = 1,groups = {"regression"})
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
    
    @Story("End-to-End Order Workflow - check authentication")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify logged user authentication")
    //add authenticated user verification
    @Test(priority = 2, dependsOnMethods = "login",groups = {"regression"})
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
    
    @Story("End-to-End Order Workflow - create order")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify order creating successfully")
    //Add create order
    @Test(priority = 3, dependsOnMethods = "verifyAuthenticatedUser",groups = {"regression"})
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
    
    @Story("End-to-End Order Workflow - valid payment")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify payment validation is successfull")
    //add payment validation
    @Test(priority = 4, dependsOnMethods = "createOrder",groups = {"regression"})
    public void validatePayment() {

        PaymentBusinessValidator.validatePaymentMethod("CARD");

        PaymentBusinessValidator.validateAmount(99.99);
    }
    
    @Story("End-to-End Order Workflow - created order retrive")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify retriveing created order successfully")
    //retrive created order
    @Test(priority = 5, dependsOnMethods = "createOrder",groups = {"regression"})
    public void verifyCreatedOrder() {

    	Response response =
    	        orderApi.getOrderById(OrderTestData.CART_ID);

    	ResponseValidator.validateStatusCode(response, 200);
    	response.then()
                .body("id", equalTo(OrderTestData.CART_ID))
                .body("userId", notNullValue())
                .body("products", notNullValue());
    }
    
    @Story("End-to-End Order Workflow - update and validate order")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify updating order successfully and validating it.")
    //update the order
    @Test(priority = 6, dependsOnMethods = "verifyCreatedOrder",groups = {"regression"})
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