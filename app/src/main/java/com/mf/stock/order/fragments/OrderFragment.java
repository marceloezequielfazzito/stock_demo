package com.mf.stock.order.fragments;



import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mf.stock.MainActivity;
import com.mf.stock.R;
import com.mf.stock.order.adapter.OrderListAdapter;
import com.mf.stock.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment {

    private OrderFragmentListener orderFragmentListener;
    private ListView listViewOrders;
    private OrderListAdapter orderListAdapter;
    private List<Order> orders = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.order_fragment,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listViewOrders = (ListView) getView().findViewById(R.id.listViewOrders);
        orders = (List<Order>) getArguments().getSerializable(MainActivity.ORDER_LIST);
        orderListAdapter = new OrderListAdapter(orders,getActivity().getApplicationContext());
        listViewOrders.setAdapter(orderListAdapter);
        listViewOrders.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                orderFragmentListener.onOrderClick(orderListAdapter.getOrderAtPosition(position));
                return false;
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        orderFragmentListener = (OrderFragmentListener) activity;
    }




}
