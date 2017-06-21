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

        //初始化异常处理，暂时先注掉，方便处理异常
        //CrashHandler.getInstance().init(this);

    }
}
