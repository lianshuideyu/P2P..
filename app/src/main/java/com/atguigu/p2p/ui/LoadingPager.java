package com.atguigu.p2p.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.atguigu.p2p.R;
import com.atguigu.p2p.utils.HttpUtils;
import com.atguigu.p2p.utils.UIUtils;

/**
 * Created by Administrator on 2017/6/23.
 */

public abstract class LoadingPager extends FrameLayout {
    private Context context;

    private View loadingView ;
    private View errorView;
    private View sucessView;

    private int STATE_LOADING = 1; //加载中
    private int STATE_ERROR = 2; //加载失败
    private int STATE_SUCCESS = 3; //加载成功

    private int current_state = STATE_LOADING;

    public LoadingPager(Context context) {
        super(context);
        this.context = context;
        
        //添加布局
        initView();
    }


    public LoadingPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        
        //添加布局
        initView();
    }
    
    //添加三个布局
    private void initView() {

        if(loadingView == null) {
            loadingView = View.inflate(context, R.layout.page_loading,null);
            this.addView(loadingView);//将加载视图添加到该 Framelayout上
        }

        if(errorView == null) {
            errorView = View.inflate(context, R.layout.page_error,null);
            this.addView(errorView);//将加载视图添加到该 Framelayout上
        }

        //然后需要展示布局
        showSafeView();

    }

    /**
     * 展示布局
     */
    private void showSafeView() {
        //要保证在主线程执行，所以要先判断是否在主线程
        UIUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                showView();
            }
        });


    }

    /**
     * 根据状态的不同来展示不同的页面
     */
    private void showView() {
        //是否 展示错误页面
        errorView.setVisibility(current_state == STATE_ERROR ? View.VISIBLE : View.INVISIBLE);

        loadingView.setVisibility(current_state == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);


        if(sucessView == null) {
//            sucessView = View.inflate(context,getViewId(),null);//动态添加,实际调用的是子类的getViewId()方法
            sucessView = getView();//动态添加,实际调用的是子类的getViewId()方法
            this.addView(sucessView);
        }
        sucessView.setVisibility(current_state == STATE_SUCCESS ? View.VISIBLE : View.INVISIBLE);

    }

    /**
     * 根据不同的网络状态加载相应的页面
     *
     */
    public void loadData(){
        String url = getUrl();
        if(TextUtils.isEmpty(url)) {//判断是否要联网
            //此时 需要加载不需联网的页面
            current_state = STATE_SUCCESS;
            showSafeView();

            //回传信息
            setResult(sucessView,"");
        }else {

//        Log.e("TAG","测试url==" + url);
            HttpUtils.getInstance().get(url, new HttpUtils.MyHttpClickListener() {
                @Override
                public void onSuccess(String content) {
                    //联网成功
                    Log.e("TAG", "LoadingPager联网成功==" + content);
                    //processData(content);
                    if(!TextUtils.isEmpty(content)) {

                        if(content.indexOf("title") > 0) {//错误的页面为H5页面信息
                            //此时为错误的加载页面,一般为网址错误
                            current_state = STATE_ERROR;
                        }else {
                            current_state = STATE_SUCCESS;

                            LoadingPager.this.setResult(sucessView, content);
//                    setJson(content);

                        }
                    }else {
                        current_state = STATE_LOADING;

                    }
                    showSafeView();//重新显示一下布局

                }

                @Override
                public void onFailure(String content) {
                    //联网失败
                    Log.e("TAG", "LoadingPager联网失败==" + content);
                    current_state = STATE_ERROR;

                    showSafeView();
                }
            });

        }

    }

//    protected abstract void setJson(String json);

    public abstract String getUrl();


//    public abstract int getViewId() ;//由子类实现
    public abstract View getView() ;//由子类实现

    //加载页面的方法
    protected abstract void setResult(View sucessView, String json);
}
