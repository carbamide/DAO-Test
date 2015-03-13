package com.decisionpt.daotest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.decisionpt.daotest.dummy.Item;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by jbarrow on 3/13/15.  Yay!
 */

class ItemAdapter extends ArrayAdapter<Item>
{
    final Context context;
    final List<Item> list;
    final SimpleDateFormat dateFormatter;

    public ItemAdapter(Context context_, List<Item> list_) {
        super(context_, android.R.layout.simple_list_item_2, list_);

        this.context = context_;
        this.list = list_;

        dateFormatter = new SimpleDateFormat("h:mm a", Locale.US);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        MyItemHolder holder;

        if (row == null) {
            if (parent == null) {
                return null;
            }

            LayoutInflater inflater = LayoutInflater.from(context);

            row = inflater.inflate(android.R.layout.simple_list_item_2, null);

            holder = new MyItemHolder();
            holder.textView = (TextView)row.findViewById(android.R.id.text1);
            holder.dateView = (TextView)row.findViewById(android.R.id.text2);

            row.setTag(holder);
        }
        else {
            holder = (MyItemHolder)row.getTag();
        }

        Item item = list.get(position);

        holder.textView.setText(item.getContent());
        holder.dateView.setText(dateFormatter.format(item.getCreatedAt()));
        return row;
    }

    class MyItemHolder {
        TextView textView;
        TextView dateView;
    }
}