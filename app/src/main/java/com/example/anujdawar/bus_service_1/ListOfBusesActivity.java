package com.example.anujdawar.bus_service_1;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class ListOfBusesActivity extends AppCompatActivity
{
    protected ListView lvProduct;
    protected productListAdapter adapter;
    protected List<product> mProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_buses);

        lvProduct = (ListView) findViewById(R.id.availaleBusesListViewID);
        mProductList = new ArrayList<>();

        mProductList.add(new product("PB1234"));
        mProductList.add(new product("PB1235"));
        mProductList.add(new product("PB1236"));
        mProductList.add(new product("PB1237"));

        adapter = new productListAdapter(getApplicationContext(), mProductList);
        lvProduct.setAdapter(adapter);

        this.registerForContextMenu(lvProduct);
    }
}