package com.Inventory.Inventory;

import java.util.List;

public class OrderCreatedEvent {
    private int orderId;
    private int customerId;
    private List<ProductoDto> productList;
    private double totalAmount;

    // Getters y Setters

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<ProductoDto> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductoDto> productList) {
        this.productList = productList;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
