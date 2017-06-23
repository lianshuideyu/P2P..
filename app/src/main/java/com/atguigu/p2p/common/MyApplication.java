package com.atguigu.p2p.common;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by Administrator on 2017/6/20.
 */

public class MyApplication extends Application {

    private static Context context;

    private static Thread mainThread;
    private static int  threadid;
    private static Handler handler;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;

        //初始化异常处理，暂时先注掉，方便处理异常
        //CrashHandler.getInstance().init(this);


        threadid = android.os.Process.myPid();
        handler = new Handler();
    }

    public static Thread getMainThread() {
        return mainThread;
    }

    public static int getThreadid() {
        return threadid;
    }


    public static Handler getHandler() {
        return handler;
    }


}
