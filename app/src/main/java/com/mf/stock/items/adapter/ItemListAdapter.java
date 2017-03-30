package com.mf.stock.items.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mf.stock.R;
import com.mf.stock.model.Item;

import java.util.List;


public class ItemListAdapter extends BaseAdapter {

    private List<Item> items;
    private Context context;
    private boolean hasBatch;

    public ItemListAdapter(List<Item> items, boolean hasBatch ,Context context) {
        this.items = items;
        this.context = context;
        this.hasBatch = hasBatch;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public Item getOrderItemAtPosition(int position){
        return items.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            // Create a new rowView into the list.
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.itemlist_item, parent, false);
        }

        final Item item = items.get(position);
        ((TextView) convertView.findViewById(R.id.textViewCodeValue)).setText(String.valueOf(item.getCode()));
        ((TextView) convertView.findViewById(R.id.textViewDescription)).setText(item.getDescription());
        ((TextView) convertView.findViewById(R.id.textViewItemQuantityValue)).setText(String.valueOf(item.getQuantity()));

        final EditText editTextQuantityProgress = (EditText) convertView.findViewById(R.id.editTextQuantityProgress);
        ImageButton imageButtonAdd = (ImageButton) convertView.findViewById(R.id.imageButtonAdd);
        ImageButton imageButtonSubtract = (ImageButton) convertView.findViewById(R.id.imageButtonSubtract);

       if(!hasBatch){
         editTextQuantityProgress.setText(String.valueOf(item.getProgress()));
         editTextQuantityProgress.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {

             }

             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {

             }

             @Override
             public void afterTextChanged(Editable s) {
                 String text = s.toString();
                 int number = 0;
                 if(!text.isEmpty()){
                    number = Integer.parseInt(text);
                 }
                if(number > item.getQuantity()) {
                   number = item.getQuantity();
                   s.replace(0, s.length(),String.valueOf(item.getQuantity()));
                }
                item.setProgress(number);
             }
         });

         imageButtonAdd.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                  item.addItem();
                  editTextQuantityProgress.setText(String.valueOf(item.getProgress()));
               }
           });

           imageButtonSubtract.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   item.subtractItem();
                   editTextQuantityProgress.setText(String.valueOf(item.getProgress()));
               }
           });

       }else {
            removeView(editTextQuantityProgress);
            removeView(imageButtonAdd);
            removeView(imageButtonSubtract);
        }
        return convertView;
    }

    private void removeView(View view) {
        if(view != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }
}
