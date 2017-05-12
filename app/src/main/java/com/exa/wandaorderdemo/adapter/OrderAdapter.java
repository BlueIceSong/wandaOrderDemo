package com.exa.wandaorderdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.exa.wandaorderdemo.R;
import com.exa.wandaorderdemo.model.Order;

import java.util.List;

/**
 * Created by Song on 2017/5/12.
 */

public class OrderAdapter extends BaseAdapter{
    private List<Order> mOrderList;
    private Context mContext;

    public OrderAdapter (Context context, List<Order> orderList) {
        this.mContext = context;
        this.mOrderList = orderList;
    }

    @Override
    public int getCount() {
        int size = mOrderList == null ? 0 : mOrderList.size();
        return size;
    }

    @Override
    public Object getItem(int position) {
        return mOrderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.form_item,null);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.item_name);
            viewHolder.editText = (EditText) convertView.findViewById(R.id.item_value);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
       viewHolder.textView.setText(mOrderList.get(position).order_name);
        return convertView;
    }

    private class ViewHolder{
        private TextView textView;
        private EditText editText;
    }
}
