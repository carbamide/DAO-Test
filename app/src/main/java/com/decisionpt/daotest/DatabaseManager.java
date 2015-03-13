package com.decisionpt.daotest;

import android.database.sqlite.SQLiteDatabase;

import com.decisionpt.daotest.dummy.DaoMaster;
import com.decisionpt.daotest.dummy.DaoSession;
import com.decisionpt.daotest.dummy.ItemDao;

/**
 * Created by jbarrow on 3/13/15.  Yay!
 */

public class DatabaseManager
{
    private static DatabaseManager instance = null;
    public final ItemDao itemDao;

    private DatabaseManager() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(MyApplication.getContext(), "items-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();

        itemDao = daoSession.getItemDao();
    }

    public static DatabaseManager sharedInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }

        return instance;
    }
}
