package com.atguigu.p2p.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.p2p.R;
import com.atguigu.p2p.common.AppManager;
import com.atguigu.p2p.utils.UIUtils;

public class SplashActivity extends AppCompatActivity {

    private TextView splash_tv_version;

    private RelativeLayout activity_splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //添加到Activity的管理栈
        AppManager.getInstance().addActivity(this);

        activity_splash = (RelativeLayout) findViewById(R.id.activity_splash);
        splash_tv_version = (TextView) findViewById(R.id.splash_tv_version);

        initView();

        initData();

        initListener();
    }

    private void initView() {

        //设置版本号
        splash_tv_version.setText(
                UIUtils.getVersionFromat(
                        splash_tv_version.getText().toString(), getVersion()
                                        )
                                );

    }

    private String getVersion() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            //给用户看的
            String versionName = packageInfo.versionName;

            //versionCode应用市场用来区分版本有没有更新
            int versionCode = packageInfo.versionCode;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return "";
    }

    private void initData() {
        /**
         *  动画效果的设置
         */
        final AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(1500);

        alphaAnimation.setFillAfter(true);
        activity_splash.startAnimation(alphaAnimation);

        //动画的监听
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画播放结束进入主主页面
                if (isLogin()) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    //进入注册页面
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                //清除动画
                activity_splash.clearAnimation();

                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    private void initListener() {


    }

    /**
     * 判断是否为第一次登录
     *
     * @return
     */
    private boolean isLogin() {

        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //推出的时候关闭移除
        AppManager.getInstance().removeActivity(this);
    }
}
