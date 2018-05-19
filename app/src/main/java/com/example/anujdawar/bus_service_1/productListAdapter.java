package com.example.anujdawar.bus_service_1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class productListAdapter extends BaseAdapter
{
    private Context mContext;
    private List<product> mProductList;

    public productListAdapter(Context mContext, List<product> mProductList)
    {
        this.mContext = mContext;
        this.mProductList = mProductList;
    }

    @Override
    public int getCount() {
        return mProductList.size();
    }

    @Override
    public Object getItem(int i) {
        return mProductList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(mContext, R.layout.bus_number, null);
        TextView tvBusNumber = (TextView) v.findViewById(R.id.busNumberTVid);

        tvBusNumber.setText(mProductList.get(i).getMyBusNumber());
        v.setTag(mProductList.get(i).getID());

        return v;
    }
}
