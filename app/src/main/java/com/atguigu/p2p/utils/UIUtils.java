package com.atguigu.p2p.utils;

import android.content.Context;
import android.view.View;

import com.atguigu.p2p.common.MyApplication;

/**
 * Created by Administrator on 2017/6/20.
 */

public class UIUtils {

    public static View findViewById(int id){

        return View.inflate(getContext(),id,null);
    }


    /*
        * 返回一个上下文
        * */
    private static Context getContext() {

        return MyApplication.getContext();
    }

    /*
   * 格式化字符串 - 占位字符
   * */
    public static String getVersionFromat(int id, String value){
        //Log.e("TAG","key==" + key);
        //格式化
        String version = String.format(getString(id), value);

        return version;
    }

    /*
   * 从string文件获取字符串
   * */
    private static String getString(int id) {


        return getContext().getResources().getString(id);
    }

    //将dp转化为px
    public static int dp2px(int dp){
        //获取手机密度
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5);//实现四舍五入
    }

    public static int px2dp(int px){
        //获取手机密度
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);//实现四舍五入
    }

    public static void runOnUiThread(Runnable runnable){
        //比较pid来判断是否在主线程
        if(android.os.Process.myTid() == MyApplication.getThreadid()) {
            runnable.run();
        }else {
            //此时是在子线程中执行的需要切换到主线程，直接交给handler处理
            MyApplication.getHandler().post(runnable);

        }

    }
}
