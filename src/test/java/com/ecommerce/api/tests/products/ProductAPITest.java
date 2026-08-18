package com.ecommerce.api.tests.products;

import com.ecommerce.api.testdata.ProductTestData;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import io.restassured.response.Response;

import com.ecommerce.api.api.ProductApi;
import com.ecommerce.api.base.BaseTest;
import com.ecommerce.api.models.ProductRequest;
import com.ecommerce.api.utils.ResponseValidator;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

@Epic("E-Commerce API")
@Feature("Product Management")
public class ProductAPITest extends BaseTest{
	
	private ProductApi productApi;
	
	@Story("Get Product")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verify getting all product successfully")
	@Test(priority = 1,groups = {"smoke", "regression"})
	public void getAllProducts() {

	    productApi = new ProductApi(requestSpec);

	    Response response = productApi.getAllProducts();

	    ResponseValidator.validateSuccessResponse(response, 200);
	    
	    response.then()
	        .log().ifValidationFails()

	        // Validate products array
	        .body("products.size()", greaterThan(0))

	        // Validate first product
	        .body("products[0].id", notNullValue())
	        .body("products[0].title", notNullValue())
	        /*.body("products[0].rating.rate",
	                allOf(
	                    greaterThanOrEqualTo(0.0f),
	                    lessThanOrEqualTo(5.0f)
	                ))
	        .body("products[0].rating.count",
	                greaterThanOrEqualTo(0))*/

	        // Validate every product
	        .body("products.id", everyItem(notNullValue()))
	        .body("products.price", everyItem(notNullValue()))
	        .body("products.category", everyItem(notNullValue()))
	        .body("products.title", everyItem(notNullValue()))

	        // Validate pagination metadata
	        .body("total", greaterThan(0))
	        .body("skip", greaterThanOrEqualTo(0))
	        .body("limit", greaterThan(0));
	}
	
	@Story("Get Product using ID")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verify getting product details using ID successfully")
	@Test(priority = 2,groups = {"smoke", "regression"})
	public void getProductById() {

	    productApi = new ProductApi(requestSpec);

	    Response response =
	            productApi.getProductById(ProductTestData.PRODUCT_ID);

	    ResponseValidator.validateSuccessResponse(response, 200);

	    response.then()
	            .log().ifValidationFails()
	            .body("id", equalTo(ProductTestData.PRODUCT_ID))
	            .body("title", notNullValue())
	            .body("price", notNullValue())
	            .body("category", notNullValue());
	}
	
	@Story("Create Product")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verify creating product successfully")
	@Test(priority = 3,groups = {"regression"})
	public void createProduct() {

	    ProductRequest pr = new ProductRequest();

	    pr.setTitle(ProductTestData.PRODUCT_TITLE);
	    pr.setPrice(ProductTestData.PRODUCT_PRICE);
	    pr.setDescription(ProductTestData.PRODUCT_DESCRIPTION);
	    pr.setCategory(ProductTestData.PRODUCT_CATEGORY);
	    pr.setImage(ProductTestData.PRODUCT_IMAGE);

	    productApi = new ProductApi(requestSpec);
	    Response response = productApi.createProduct(pr);

	    ResponseValidator.validateSuccessResponse(response, 201);
	    response.then()
	        .log().all()
	        .body("id", notNullValue())
	        .body("title", equalTo(ProductTestData.PRODUCT_TITLE))
	        .body("price", equalTo((float) ProductTestData.PRODUCT_PRICE))
	        .body("description", equalTo(ProductTestData.PRODUCT_DESCRIPTION));
	}
	
	@Story("Update Product")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verify updating product successfully")
	@Test(priority =4,groups = {"regression"})
	public void updateProduct() {

	    ProductRequest productRequest = new ProductRequest();

	    productRequest.setTitle(ProductTestData.UPDATED_PRODUCT_TITLE);
	    productRequest.setPrice(ProductTestData.UPDATED_PRODUCT_PRICE);
	    productRequest.setDescription(ProductTestData.UPDATED_PRODUCT_DESCRIPTION);
	    productRequest.setImage(ProductTestData.PRODUCT_IMAGE);
	    productRequest.setCategory(ProductTestData.UPDATED_PRODUCT_CATEGORY);
	    
	    productApi = new ProductApi(requestSpec);
		Response response = productApi.updateProduct(ProductTestData.PRODUCT_ID,productRequest);

		ResponseValidator.validateSuccessResponse(response, 200);
	    response
	    .then()
	        .log().ifValidationFails()
	        .body("id", equalTo(ProductTestData.PRODUCT_ID))
	        .body("title", equalTo(ProductTestData.UPDATED_PRODUCT_TITLE))
	        .body("price", equalTo((float) ProductTestData.UPDATED_PRODUCT_PRICE))
	        .body("description", equalTo(ProductTestData.UPDATED_PRODUCT_DESCRIPTION))
	        .body("category", equalTo(ProductTestData.UPDATED_PRODUCT_CATEGORY));
	}
	
	@Story("Delete Product")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verify deleting product successfully")
	@Test(priority =5,groups = {"regression"})
	public void deleteProduct() {
		
		productApi = new ProductApi(requestSpec);
		Response response = productApi.deleteProduct(ProductTestData.PRODUCT_ID);
		ResponseValidator.validateStatusCode(response, 200);
		response
			.then()
			.log().ifValidationFails();
	}
	
}
