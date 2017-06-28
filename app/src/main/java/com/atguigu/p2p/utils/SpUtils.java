package com.atguigu.p2p.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/6/26.
 */

public class SpUtils {

    /**
     * 保存文字内容
     * @param context
     * @param key
     * @param value
     */
    public static void saveString(Context context,String key,String value){
        SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);

        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key,value);
        edit.commit();
    }

    /**
     * 保存头像的url
     * @param context
     * @param value
     */
    public static void saveImageUrl(Context context,String value){
        SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);

        SharedPreferences.Editor edit = sp.edit();
        edit.putString("imageurl",value);
        edit.commit();
    }

    /**
     * 读取图片的url
     * @param context
     * @return
     */
    public static String getImageUrl(Context context){
        SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);

        String value = sp.getString("imageurl", "");

        return value;
    }

    /**
     *读取保存信息
     * @param context
     * @param key
     * @return
     */
    public static String getSave(Context context,String key){
        SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);

        String value = sp.getString(key, "");

        return value;
    }

    /**
     * 清除缓存
     * @param context
     * @param key
     */
    public static void clearSave(Context context,String key){
        SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);

        sp.edit().clear().commit();

    }

    /**
     * 保存手势识别是否选中
     * @param context
     * @param value
     */
    public static void saveToggleIscheck(Context context,boolean value){
        SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);

        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("ischeck",value);
        edit.commit();
    }

    /**
     * 得到手势识别是否选中
     * @param context
     * @return
     */
    public static boolean getToggleIscheck(Context context){
        SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);

        boolean value = sp.getBoolean("ischeck", false);

        return value;
    }

}
