package com.atguigu.p2p.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.atguigu.p2p.R;
import com.atguigu.p2p.common.AppManager;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //添加到Activity的管理栈
        AppManager.getInstance().addActivity(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //推出的时候关闭移除
        AppManager.getInstance().removeActivity(this);
    }
}
