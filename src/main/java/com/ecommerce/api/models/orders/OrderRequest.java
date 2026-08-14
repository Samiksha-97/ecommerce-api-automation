package com.ecommerce.api.models.orders;

import java.util.List;

public class OrderRequest {

    private int userId;
    private List<OrderProduct> products;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<OrderProduct> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProduct> products) {
        this.products = products;
    }
}