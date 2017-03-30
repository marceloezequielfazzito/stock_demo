package com.mf.stock;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.mf.stock.api.OrderAPI;
import com.mf.stock.items.fragments.ItemFragment;
import com.mf.stock.items.fragments.ItemFragmentListener;
import com.mf.stock.model.Item;
import com.mf.stock.model.factory.OrderFactory;
import com.mf.stock.order.fragments.OrderFragment;
import com.mf.stock.order.fragments.OrderFragmentListener;
import com.mf.stock.model.Order;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , OrderFragmentListener,ItemFragmentListener {

    public static final String ORDER_LIST = "order_list";
    private OrderFragment orderFragment;
    private Fragment visibleFragment;
    private SharedPreferences sharedPref;
    private OrderAPI orderAPI;
    private List<Order> orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        orderAPI = new OrderAPI();
        orders = new ArrayList<>();
        if(savedInstanceState == null){
            getOrdersFromServer();
        }

        createOrderFragment();

        sharedPref = getApplicationContext().getSharedPreferences(
                getString(R.string.shared_preference_name), Context.MODE_PRIVATE);

        sharedPref.edit().putBoolean(getString(R.string.has_batch),false).commit();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ORDER_LIST,new ArrayList<>(orders));
    }

    private void getOrdersFromServer() {
        orders = OrderFactory.createOrders(orderAPI.getOrders());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_server_synch) {
            getOrdersFromServer();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.order_preparation) {
            createOrderFragment();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void createOrderFragment() {
        orderFragment = new OrderFragment();
        visibleFragment = orderFragment;
        Bundle args = new Bundle();
        args.putSerializable(ORDER_LIST,new ArrayList<>(orders));
        orderFragment.setArguments(args);
        replaceFragment(orderFragment,"OrderFragment");
    }

    private void replaceFragment(Fragment fragment,String tag) {
        if(!fragment.isAdded()) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out,android.R.animator.fade_in, android.R.animator.fade_out);
            transaction.replace(R.id.frame_layout_principal, fragment);
            transaction.addToBackStack(tag);
            transaction.commit();
        }
    }

    @Override
    public void onOrderClick(Order order) {
        ItemFragment itemFragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putSerializable(ItemFragment.ORDER,order);
        itemFragment.setArguments(args);
        if(!itemFragment.isAdded()) {
          replaceFragment(itemFragment,visibleFragment.getClass().getName());
        }
    }

    @Override
    public void onOrderItemClick(Item item) {
        Toast.makeText(this,"item clicked" + item.getCode(),Toast.LENGTH_LONG).show();
    }
}
