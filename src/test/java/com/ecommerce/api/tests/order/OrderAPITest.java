package com.ecommerce.api.tests.order;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import static org.hamcrest.Matchers.equalTo;
import com.ecommerce.api.utils.ResponseValidator;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Arrays;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ecommerce.api.api.OrderApi;
import com.ecommerce.api.base.BaseTest;
import com.ecommerce.api.models.orders.OrderProduct;
import com.ecommerce.api.models.orders.OrderRequest;
import com.ecommerce.api.testdata.order.OrderTestData;

import io.restassured.response.Response;


@Epic("E-Commerce API")
@Feature("Order Management")
public class OrderAPITest extends BaseTest {

    private OrderApi orderApi;

    @BeforeClass
    public void setupOrderApi() {
        orderApi = new OrderApi(requestSpec);
    }
    
    @Story("Order Management")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1)
    public void getAllOrders() {

        Response response = orderApi.getAllOrders();
        
        ResponseValidator.validateSuccessResponse(response, 200);
        response.then()
                .log().ifValidationFails()
                .body("carts", notNullValue());
    }
    
    @Story("Order Management")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 2)
    public void getOrderById() {

        Response response =
                orderApi.getOrderById(OrderTestData.CART_ID);

        ResponseValidator.validateSuccessResponse(response, 200);
        response.then()
                .log().ifValidationFails()
                .body("id", equalTo(OrderTestData.CART_ID))
                .body("userId", notNullValue())
                .body("products", notNullValue())
                .body("totalProducts", notNullValue())
                .body("totalQuantity", notNullValue());
    }
    
    @Story("Order Management")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 3)
    public void createOrder() {

        OrderProduct orderProduct = new OrderProduct();

        orderProduct.setId(OrderTestData.PRODUCT_ID);
        orderProduct.setQuantity(OrderTestData.PRODUCT_QUANTITY);

        OrderRequest orderRequest = new OrderRequest();

        orderRequest.setUserId(OrderTestData.USER_ID);
        orderRequest.setProducts(Arrays.asList(orderProduct));

        Response response = orderApi.createOrder(orderRequest);

        ResponseValidator.validateSuccessResponse(response, 201);
        response.then()
                .log().all()
                .body("id", notNullValue())
                .body("userId", equalTo(OrderTestData.USER_ID))
                .body("products", notNullValue())
		        .body("totalProducts", equalTo(1))
		        .body("totalQuantity", equalTo(OrderTestData.PRODUCT_QUANTITY));
    }
    
    @Story("Order Management")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 4)
    public void updateOrder() {
    	
        OrderProduct orderProduct = new OrderProduct();

        orderProduct.setId(OrderTestData.PRODUCT_ID);
        orderProduct.setQuantity(OrderTestData.UPDATED_PRODUCT_QUANTITY);

        OrderRequest orderRequest = new OrderRequest();

        orderRequest.setUserId(OrderTestData.USER_ID);
        orderRequest.setProducts(Arrays.asList(orderProduct));
        
        Response response = orderApi.updateOrder(OrderTestData.USER_ID, orderRequest);

        ResponseValidator.validateSuccessResponse(response, 200);
        response.then()
        .log().ifValidationFails()
        .body("id", equalTo(OrderTestData.CART_ID))
        .body("userId", equalTo(OrderTestData.USER_ID))
        .body("products[0].id", equalTo(OrderTestData.PRODUCT_ID))
        .body("products[0].quantity",
                equalTo(OrderTestData.UPDATED_PRODUCT_QUANTITY))
        .body("totalProducts", equalTo(1))
        .body("totalQuantity",
                equalTo(OrderTestData.UPDATED_PRODUCT_QUANTITY));
    }
  
}