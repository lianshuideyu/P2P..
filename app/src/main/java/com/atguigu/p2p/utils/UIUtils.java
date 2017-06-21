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
}
