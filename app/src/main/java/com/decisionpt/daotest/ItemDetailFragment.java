package com.decisionpt.daotest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.decisionpt.daotest.dummy.Item;
import com.decisionpt.daotest.dummy.ItemDao;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ItemDetailFragment extends Fragment
{
    private Item item;

    public static final String ARG_ITEM_ID = "item_id";
    public ItemDetailFragment()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        List<Item> items = DatabaseManager.sharedInstance().itemDao.queryBuilder().where(ItemDao.Properties.Id.eq(getArguments().getString(ARG_ITEM_ID))).list();

        if (items != null) {
            if (items.size() == 0) {
                return;
            }

            item = items.get(0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);

        if (item != null) {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy @ h:mm a", Locale.US);

            ((TextView) rootView.findViewById(R.id.id)).setText(String.valueOf(item.getId()));
            ((TextView) rootView.findViewById(R.id.content)).setText(String.valueOf(item.getContent()));
            ((TextView) rootView.findViewById(R.id.createdAt)).setText(dateFormatter.format(item.getCreatedAt()));
        }

        return rootView;
    }
}
