package com.mf.stock.model.factory;

import com.mf.stock.api.request.ItemRequest;
import com.mf.stock.api.request.OrderRequest;
import com.mf.stock.model.Item;
import com.mf.stock.model.Order;
import com.mf.stock.model.OrderState;

import java.util.ArrayList;
import java.util.List;

public class OrderFactory {


    public static List<Order> createOrders(List<OrderRequest> orderRequests){

        List<Order> orders = new ArrayList<>();
        for(OrderRequest orderRequest : orderRequests){
            List<Item> items = new ArrayList<>();
            for (ItemRequest itemRequest : orderRequest.getItems()){
               //TODO : replace by builder if necessary
               items.add(new Item(itemRequest.getId(),
                       itemRequest.getDescription(),
                       itemRequest.getBarcode(),
                       itemRequest.getQuantity()));
            }
            //TODO : replace by builder if necessary
            //TODO : id must be given by persistence cache
            Order order = new Order(1L,
                    orderRequest.getOrderNumber(),
                    orderRequest.getCustomer(),
                    items,
                    OrderState.READY_TO_PREPARE);

            orders.add(order);

        }
        return orders;
    }


}
