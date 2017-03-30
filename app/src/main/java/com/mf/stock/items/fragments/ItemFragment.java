package com.mf.stock.items.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.mf.stock.R;
import com.mf.stock.items.adapter.ItemListAdapter;
import com.mf.stock.model.Order;


public class ItemFragment extends Fragment {

    public static final String ORDER = "ORDER";
    private Order order;
    private TextView textViewOrderItemNumber;
    private ItemListAdapter itemListAdapter;
    private ListView listViewItems;
    private boolean hasBatch;
    private ItemFragmentListener itemFragmentListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.items_fragment,container,false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Context context = getActivity().getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.shared_preference_name), Context.MODE_PRIVATE);
        hasBatch = sharedPref.getBoolean(context.getString(R.string.has_batch), true);
        order = (Order) getArguments().getSerializable(ORDER);
        textViewOrderItemNumber = (TextView) getView().findViewById(R.id.textViewOrderItemNumber);
        textViewOrderItemNumber.setText(String.valueOf(order.getOrderNumber()));
        listViewItems = (ListView) getView().findViewById(R.id.listViewItems);
        itemListAdapter = new ItemListAdapter(order.getItems(),hasBatch,context);
        listViewItems.setAdapter(itemListAdapter);
        if(hasBatch){
           listViewItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
               @Override
               public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                   itemFragmentListener.onOrderItemClick(itemListAdapter.getOrderItemAtPosition(position));
                   return false;
               }
           });
        }

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        itemFragmentListener = (ItemFragmentListener) activity;
    }


}
