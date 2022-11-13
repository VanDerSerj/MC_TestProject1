package com.group.MCTestProject.dto;

import java.util.List;

public class OrdersResponse {
    private List<OrdersDTO> orders;

    public OrdersResponse(List<OrdersDTO> orders) {
        this.orders = orders;
    }

    public List<OrdersDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrdersDTO> orders) {
        this.orders = orders;
    }
}
