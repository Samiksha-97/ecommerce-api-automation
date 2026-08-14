package com.ecommerce.api.tests.negative;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import java.util.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ecommerce.api.api.OrderApi;
import com.ecommerce.api.base.BaseTest;
import com.ecommerce.api.config.Environment;
import com.ecommerce.api.models.orders.OrderProduct;
import com.ecommerce.api.models.orders.OrderRequest;
import com.ecommerce.api.testdata.order.OrderTestData;
import com.ecommerce.api.utils.OrderBusinessValidator;
import com.ecommerce.api.utils.ResponseValidator;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class OrderNegativeTest extends BaseTest {

	private OrderApi orderApi;

	@BeforeClass
	public void setupOrderApi() {

	    RestAssured.baseURI = Environment.BASE_URL;

	    RequestSpecification negativeRequestSpec =
	            RestAssured.given()
	                    .accept("application/json")
	                    .contentType("application/json");

	    orderApi = new OrderApi(negativeRequestSpec);
	}
	
    @Test
    public void getOrderWithInvalidId() {

        Response response =
                orderApi.getOrderById(999999);

        ResponseValidator.validateStatusCode(response, 404);
        response.then()
                .log().ifValidationFails()
                .body("message",
                        equalTo("Cart with id '999999' not found"));
    }
    
    /**
     * DummyJSON accepts negative quantities and returns 201.
     * This is a mock API limitation.
     * Business validation is handled separately by OrderBusinessValidator.
     */
    @Test
    public void createOrderWithInvalidUserId() {

        OrderProduct orderProduct = new OrderProduct();

        orderProduct.setId(OrderTestData.PRODUCT_ID);
        orderProduct.setQuantity(OrderTestData.PRODUCT_QUANTITY);

        OrderRequest orderRequest = new OrderRequest();

        orderRequest.setUserId(999999);
        orderRequest.setProducts(Arrays.asList(orderProduct));

        Response response =
                orderApi.createOrder(orderRequest);

        ResponseValidator.validateStatusCode(response, 404);
        response.then()
                .log().ifValidationFails()
                .body("message",
                        equalTo("User with id '999999' not found"));
    }
    
    @Test
    public void verifyApiBehaviorForInvalidQuantity() {

        OrderProduct orderProduct = new OrderProduct();

        orderProduct.setId(OrderTestData.PRODUCT_ID);
        orderProduct.setQuantity(-1);

        OrderRequest orderRequest = new OrderRequest();

        orderRequest.setUserId(OrderTestData.USER_ID);
        orderRequest.setProducts(Arrays.asList(orderProduct));

        Response response =
                orderApi.createOrder(orderRequest);

        response.then()
                .log().all();
    }
    
    @Test
    public void validateInvalidQuantityBusinessRule() {

        int quantity = -1;

        try {

            OrderBusinessValidator.validateQuantity(quantity);

            throw new AssertionError(
                    "Expected invalid quantity validation to fail");

        } catch (IllegalArgumentException e) {

            org.testng.Assert.assertEquals(
                    e.getMessage(),
                    "Product quantity must be greater than zero");
        }
    }
}