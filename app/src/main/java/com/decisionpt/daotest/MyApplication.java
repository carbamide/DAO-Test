package com.decisionpt.daotest;

import android.app.Application;
import android.content.Context;

/**
 * Created by jbarrow on 3/13/15.  Yay!
 */

public class MyApplication extends Application
{

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}