package com.atguigu.p2p.common;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2017/6/20.
 */

public class MyApplication extends Application {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
    }
}
