package com.atguigu.p2p.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/6/26.
 */

public class SpUtils {


    public static void saveString(Context context,String key,String value){
        SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);

        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key,value);
        edit.commit();
    }

    public static String getSave(Context context,String key){
        SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);

        String value = sp.getString(key, "");

        return value;
    }


}
