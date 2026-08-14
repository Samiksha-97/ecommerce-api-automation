package com.ecommerce.api.endpoints;

public class Endpoints {
	
	//product
	public static final String PRODUCTS = "/products";
	
	public static final String PRODUCT_BY_ID = "/products/{id}";
	
	public static final String CREATE_PRODUCT = "/products/add";
	
	public static final String UPADATE_PRODUCT = "/products/{id}";
	
	 public static final String DELETE_PRODUCT = "/products/{id}";
	 
	 //user
	 public static final String USERS = "/users";

	 public static final String USER_BY_ID = "/users/{id}";

	 public static final String CREATE_USER = "/users/add";

	 public static final String UPDATE_USER = "/users/{id}";

	 public static final String DELETE_USER = "/users/{id}";
	 
	 //login and access token
	 public static final String LOGIN = "/auth/login";

	 public static final String AUTH_ME = "/auth/me";
	 
	 //order
	 public static final String CARTS = "/carts";

	 public static final String CART_BY_ID = "/carts/{id}";

	 public static final String CREATE_CART = "/carts/add";

	 public static final String UPDATE_CART = "/carts/{id}";

}
