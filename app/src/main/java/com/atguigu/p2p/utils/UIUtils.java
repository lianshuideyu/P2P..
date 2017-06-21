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
}
