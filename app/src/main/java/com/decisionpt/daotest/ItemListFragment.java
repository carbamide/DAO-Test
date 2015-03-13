package com.decisionpt.daotest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.decisionpt.daotest.dummy.Item;
import java.util.List;

public class ItemListFragment extends ListFragment
{
    private static final String STATE_ACTIVATED_POSITION = "activated_position";
    private Callbacks mCallbacks = callbacks;
    private int mActivatedPosition = ListView.INVALID_POSITION;

    public interface Callbacks
    {
        void onItemSelected(String id);
    }

    private static final Callbacks callbacks = new Callbacks()
    {
        @Override
        public void onItemSelected(String id)
        {
        }
    };

    public ItemListFragment()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setListAdapter(new ItemAdapter(getActivity(), DatabaseManager.sharedInstance().itemDao.queryBuilder().list()));


    }

    public void updateList() {
        setListAdapter(new ItemAdapter(getActivity(), DatabaseManager.sharedInstance().itemDao.queryBuilder().list()));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }

        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id)
            {
                Toast.makeText(getActivity().getApplicationContext(), "pos: " + pos, Toast.LENGTH_SHORT).show();

                return true;
            }
        });
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);

        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach()
    {
        super.onDetach();

        mCallbacks = callbacks;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id)
    {
        super.onListItemClick(listView, view, position, id);

        List<Item> items = DatabaseManager.sharedInstance().itemDao.queryBuilder().list();

        mCallbacks.onItemSelected(String.valueOf(items.get(position).getId()));
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);

        if (mActivatedPosition != ListView.INVALID_POSITION) {
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    public void setActivateOnItemClick(boolean activateOnItemClick)
    {
        getListView().setChoiceMode(activateOnItemClick ? ListView.CHOICE_MODE_SINGLE : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position)
    {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        }
        else {
            getListView().setItemChecked(position, true);
        }

        mActivatedPosition = position;
    }


}
