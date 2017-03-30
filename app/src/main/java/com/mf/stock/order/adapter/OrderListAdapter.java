package com.mf.stock.order.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mf.stock.R;
import com.mf.stock.model.Order;

import java.util.List;


public class OrderListAdapter extends BaseAdapter {

    private List<Order> orders;
    private Context context;

    public OrderListAdapter(List<Order> orders, Context context) {
        this.orders = orders;
        this.context = context;
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Object getItem(int position) {
        return orders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public Order getOrderAtPosition(int position){
        return orders.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            // Create a new rowView into the list.
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.order_item, parent, false);
        }

        Order order = orders.get(position);
        ((TextView) convertView.findViewById(R.id.textViewOrderState)).setText(context.getResources().getString(order.getOrderState().getKey()));
        ((TextView) convertView.findViewById(R.id.textViewOrderNumber)).setText(String.valueOf(order.getOrderNumber()));
        ((TextView) convertView.findViewById(R.id.textViewClient)).setText(order.getCustomer());
        ((TextView) convertView.findViewById(R.id.textViewItemQuantity)).setText(String.valueOf(order.getTotalProgress()));
        ((TextView) convertView.findViewById(R.id.textViewPercent)).setText(order.getProgressPercent()+"%");
        ((ProgressBar) convertView.findViewById(R.id.progressBarItemOrder)).setProgress(order.getProgressPercent());


        return convertView;
    }
}
