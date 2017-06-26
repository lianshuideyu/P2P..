package com.atguigu.p2p.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.p2p.R;
import com.atguigu.p2p.base.BaseActivity;
import com.atguigu.p2p.utils.UIUtils;

import butterknife.InjectView;

import static com.atguigu.p2p.R.id.splash_tv_version;

public class SplashActivity extends BaseActivity {


    @InjectView(splash_tv_version)
    TextView splashTvVersion;
    @InjectView(R.id.activity_splash)
    RelativeLayout activity_splash;

    @Override
    protected void initListener() {

    }


    @Override
    public int getLayout() {
        return R.layout.activity_splash;
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

    @Override
    protected void initData() {
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

    @Override
    protected void initView() {
        //设置版本号
        splashTvVersion.setText(
                UIUtils.getVersionFromat(
                        R.string.verson_name, getVersion()
                )
        );
    }


    /**
     * 判断是否为第一次登录
     *
     * @return
     */
    private boolean isLogin() {

        return true;
    }

}
