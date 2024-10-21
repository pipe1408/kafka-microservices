package kafka.Billing.entity;

import kafka.Billing.dto.SelectedProductDTO;

import java.util.ArrayList;

public class InventoryEvent {

    private Integer orderId;
    private ArrayList<SelectedProductDTO> productList;
    private String status;

    public InventoryEvent() {
    }

    public InventoryEvent(Integer orderId, ArrayList<SelectedProductDTO> productList, String status) {
        this.orderId = orderId;
        this.productList = productList;
        this.status = status;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public ArrayList<SelectedProductDTO> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<SelectedProductDTO> productList) {
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
        return "InventoryEvent{" +
                "orderId='" + orderId + '\'' +
                ", productList=" + productList +
                ", status='" + status + '\'' +
                '}';
    }
}