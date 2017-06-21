package com.atguigu.p2p.utils;

import android.view.View;

import com.atguigu.p2p.common.MyApplication;

/**
 * Created by Administrator on 2017/6/20.
 */

public class UIUtils {

    public static View findViewById(int id){

        return View.inflate(MyApplication.getContext(),id,null);
    }

    public static String getVersionFromat(String key,String value){
        //Log.e("TAG","key==" + key);
        //格式化
        String version = String.format(key, value);

        return version;
    }
}
