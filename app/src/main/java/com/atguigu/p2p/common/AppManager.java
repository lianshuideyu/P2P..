package com.atguigu.p2p.common;

import android.app.Activity;
import android.util.Log;

import java.util.Stack;

/**
 * Created by Administrator on 2017/6/21.
 */

public class AppManager {

    /*
     * 单例
     * 第一步 私有化构造器
     * 第二步 创建实例
     * 第三步 创建一个方法 返回实例
     *
     * */

    private AppManager() {}

    private static AppManager appManager = new AppManager();

    private Stack<Activity> stack = new Stack<>();

    public static AppManager getInstance(){
        return appManager;
    }

    public void  removeActivity(Activity activity){

        if(activity != null) {
            for(int i = stack.size() -1 ; i >= 0; i--) {
                Activity currentActivity = stack.get(i);
                //方法一
                if(currentActivity.getClass().equals(activity.getClass())) {

                    currentActivity.finish();
                    stack.remove(i);
                }
                //方法二 依据地址值唯一的特性
//                if(currentActivity == activity) {
//
//                }

            }
        }
    }

    /**
     * 删除所有的Activity
     */
    public void removeAllActivity(){

        for(int i = stack.size()-1; i >=0 ; i--) {

            Activity currentActivity = stack.get(i);

            if(currentActivity != null) {
                currentActivity.finish();
                stack.remove(i);
            }

        }
    }

    public void addActivity(Activity activity){
        Log.e("stack", "addActivity: "+activity.getClass().toString());

        if(activity != null) {
            stack.add(activity);
        }

    }
}
