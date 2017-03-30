package com.mf.stock.api;


import com.mf.stock.api.request.ItemRequest;
import com.mf.stock.api.request.OrderRequest;

import java.util.ArrayList;
import java.util.List;

public class OrderAPI {

    public List<OrderRequest> getOrders(){

        // TODO : execute web service call on different thread

        List<ItemRequest> items = new ArrayList<>();
        items.add(new ItemRequest(2,"item 1","abc123",3));
        items.add(new ItemRequest(2,"item 2","abc123",3));
        items.add(new ItemRequest(2,"item 3","abc123",3));
        items.add(new ItemRequest(2,"item 4","abc123",3));
        items.add(new ItemRequest(2,"item 5","abc123",3));
        items.add(new ItemRequest(2,"item 6","abc123",3));
        items.add(new ItemRequest(2,"item 7","abc123",3));

        List<OrderRequest> orders = new ArrayList<>();
        orders.add(new OrderRequest(1l,"makro",items));
        orders.add(new OrderRequest(2l,"makro",items));
        orders.add(new OrderRequest(3l,"makro",items));
        orders.add(new OrderRequest(4l,"makro",items));
        orders.add(new OrderRequest(5l,"makro",items));
        orders.add(new OrderRequest(6l,"makro",items));
        return orders;
    }
}
