package com.mf.stock.model;

import java.io.Serializable;
import java.util.List;


public class Order implements Serializable{

    private Long id;
    private Long orderNumber;
    private String customer;
    private List<Item> items;
    private int itemProgress;
    private OrderState orderState;


    public Order(Long id, Long orderNumber, String customer, List<Item> items, OrderState orderState) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.items = items;
        this.orderState = orderState;
        itemProgress = 0;
    }


    public Long getId() {
        return id;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public String getCustomer() {
        return customer;
    }

    public int getItemQuantity() {
        return items.size();
    }

    public int getItemProgress() {
        return itemProgress;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public int getTotalProgress(){
        return getItemQuantity() - itemProgress;
    }

    public int getProgressPercent(){
        return  (itemProgress * 100) / getItemQuantity();
    }

    public void incrementProgress(){
        if(itemProgress < getItemQuantity()){
            itemProgress++;
        }
    }

    public List<Item> getItems() {
        return items;
    }
}
