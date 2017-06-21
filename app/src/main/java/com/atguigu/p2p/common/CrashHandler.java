package com.atguigu.p2p.common;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/6/21.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private Context context;

    //需要是单例的

    private CrashHandler() {}

    private static CrashHandler crashHandler = new CrashHandler();


    public static CrashHandler getInstance(){
        return crashHandler;
    }

    public void init(Context context) {
        //这里告诉系统 崩溃的操作 由本类处理
        Thread.setDefaultUncaughtExceptionHandler(this);

        this.context = context;

    }

    @Override
    public void uncaughtException(Thread thread, Throwable e) {
        Log.e("crash", "uncaughtException: "+e.getMessage());
        /*
        * 第一 提醒用户
        * 第二 收集异常
        * 第三 退出应用
        *
        *
        * */
        new Thread(){
            public void run(){
                Looper.prepare();
                //执行在主线程的方法
                Toast.makeText(context, "出现异常", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();

        //收集异常信息
        collection(e);
        //关闭所有activity
        AppManager.getInstance().removeAllActivity();

        //杀死当前的进程
        android.os.Process.killProcess(android.os.Process.myPid());

        //参数 除了0以外都表示非正常退出
        System.exit(0);//退出虚拟机

    }

    /**
     * 收集异常信息
     * @param e
     */
    private void collection(Throwable e) {

        //随便写一个...
        String version = Build.BOARD;

        //一般情况应发送给服务器

    }


}
