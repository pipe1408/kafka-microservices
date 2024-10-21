package com.Inventory.Inventory;

import java.util.List;

public class InventoryReservedEvent {

    private int orderId;
    private List<ProductoDto> productList;
    private String status;

    // Constructor, Getters y Setters

    public InventoryReservedEvent(int orderId, List<ProductoDto> productList, String status) {
        this.orderId = orderId;
        this.productList = productList;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<ProductoDto> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductoDto> productList) {
        this.productList = productList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "InventoryReservedEvent{" +
                "orderId='" + orderId + '\'' +
                ", productList=" + productList +
                ", status='" + status + '\'' +
                '}';
    }
}
