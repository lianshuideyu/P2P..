package com.atguigu.p2p.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.atguigu.p2p.common.AppManager;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/26.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载子类布局

        setContentView(getLayout());
        ButterKnife.inject(this);

        //将继承的Activity添加到Activity的管理栈
        AppManager.getInstance().addActivity(this);

        initView();

        initData();

        initListener();

        initTitle();
    }

    public void initTitle() {

    }

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract void initView();

    public abstract int getLayout();



    @Override
    protected void onDestroy() {
        super.onDestroy();
        //推出的时候关闭移除
        AppManager.getInstance().removeActivity(this);
    }

    /**
     * 弹吐司的方法
     */
    public void showToast(String message){
        Toast.makeText(BaseActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
