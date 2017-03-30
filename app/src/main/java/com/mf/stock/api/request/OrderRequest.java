package com.mf.stock.api.request;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class OrderRequest implements Serializable{

    @SerializedName("order_number")
    private Long orderNumber;
    @SerializedName("order_number")
    private String customer;
    @SerializedName("order_number")
    private List<ItemRequest> items;


    public OrderRequest(Long orderNumber, String customer, List<ItemRequest> items) {
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.items = items;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public String getCustomer() {
        return customer;
    }

    public List<ItemRequest> getItems() {
        return items;
    }
}
