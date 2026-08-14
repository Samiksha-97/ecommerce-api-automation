package com.ecommerce.api.api;

import com.ecommerce.api.endpoints.Endpoints;
import com.ecommerce.api.models.orders.OrderRequest;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class OrderApi extends BaseApi {

    private RequestSpecification requestSpec;

    public OrderApi(RequestSpecification requestSpec) {
    	super(requestSpec);
    }

    public Response getAllOrders() {

    	 return request()
    	            .get(Endpoints.CARTS);
    }

    public Response getOrderById(int orderId) {

    	 return request()
    	            .pathParam("id", orderId)
    	            .get(Endpoints.CART_BY_ID);
    }

    public Response createOrder(OrderRequest orderRequest) {

    	return request()
                .body(orderRequest)
                .post(Endpoints.CREATE_CART);
    }

    public Response updateOrder(int orderId, OrderRequest orderRequest) {

    	return request()
                .pathParam("id", orderId)
                .body(orderRequest)
                .put(Endpoints.UPDATE_CART);
    }
}