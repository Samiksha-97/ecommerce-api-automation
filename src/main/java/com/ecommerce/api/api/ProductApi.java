package com.ecommerce.api.api;

import com.ecommerce.api.endpoints.Endpoints;
import com.ecommerce.api.models.ProductRequest;
import io.restassured.specification.RequestSpecification;

import io.restassured.response.Response;

public class ProductApi extends BaseApi {

	private RequestSpecification requestSpec;

	public ProductApi(RequestSpecification requestSpec) {
	    super(requestSpec);
	}
	
    public Response getAllProducts() {

    	return request()
    	        .get(Endpoints.PRODUCTS);
    }

    public Response getProductById(int productId) {

        return request()
                .pathParam("id", productId)
                .get(Endpoints.PRODUCT_BY_ID);
    }

    public Response createProduct(ProductRequest productRequest) {

        return request()
                .body(productRequest)
                .post(Endpoints.CREATE_PRODUCT);
    }

    public Response updateProduct(int productId, ProductRequest productRequest) {

        return request()
                .pathParam("id", productId)
                .body(productRequest)
                .put(Endpoints.UPADATE_PRODUCT);
    }

    public Response deleteProduct(int productId) {

        return request()
                .pathParam("id", productId)
                .delete(Endpoints.DELETE_PRODUCT);
    }
}