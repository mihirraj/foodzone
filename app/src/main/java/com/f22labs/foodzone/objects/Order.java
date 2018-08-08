package com.f22labs.foodzone.objects;

public class Order {
    private int orderId;
    private int restroId;
    private String subTotal;
    private String deliveryFees;
    private String totalAmount;
    private String orderDate;
    private String deliveryTime;
    private String orderStatus;


    public Order(int orderId, int restroId, String subTotal, String deliveryFees, String totalAmount, String orderDate, String deliveryTime, String orderStatus) {
        this.orderId = orderId;
        this.restroId = restroId;
        this.subTotal = subTotal;
        this.deliveryFees = deliveryFees;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.deliveryTime = deliveryTime;
        this.orderStatus = orderStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getRestroId() {
        return restroId;
    }

    public void setRestroId(int restroId) {
        this.restroId = restroId;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getDeliveryFees() {
        return deliveryFees;
    }

    public void setDeliveryFees(String deliveryFees) {
        this.deliveryFees = deliveryFees;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", restroId=" + restroId +
                ", subTotal='" + subTotal + '\'' +
                ", deliveryFees='" + deliveryFees + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", deliveryTime='" + deliveryTime + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}
