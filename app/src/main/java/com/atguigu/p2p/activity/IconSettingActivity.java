package com.atguigu.p2p.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.p2p.R;
import com.atguigu.p2p.base.BaseActivity;
import com.atguigu.p2p.common.AppManager;
import com.atguigu.p2p.common.AppNetConfig;
import com.atguigu.p2p.utils.BitmapUtils;
import com.atguigu.p2p.utils.SpUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import butterknife.InjectView;
import butterknife.OnClick;

public class IconSettingActivity extends BaseActivity {


    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;
    @InjectView(R.id.iv_user_icon)
    ImageView ivUserIcon;
    @InjectView(R.id.tv_user_change)
    TextView tvUserChange;
    @InjectView(R.id.btn_user_logout)
    Button btnUserLogout;

    @Override
    public int getLayout() {
        return R.layout.activity_icon_setting;
    }

    @Override
    protected void initView() {
        baseBack.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        baseTitle.setText("用户设置");

        //正常情况应该从缓存中获取用户数据然后加载保存的头像链接，这里简单写
        //设置头像
        //将头像设置为圆形
        Picasso.with(this)
                .load(AppNetConfig.BASE_URL+"images/tx.png")
                //.transform(new CropCircleTransformation())//加载圆形图片，transform可以设置多个
                .transform(new MyCropCircleTransformation())
                .into(ivUserIcon);
    }


    @OnClick({R.id.base_back, R.id.tv_user_change, R.id.btn_user_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.base_back:
                finish();
                break;
            case R.id.tv_user_change:
                showToast("更换头像");
                break;
            case R.id.btn_user_logout:
                //退出登录
                showToast("退出登录");
                /**
                 * 1.清除缓存（sp，file..）
                 * 2.跳转到登录页面
                 * 3.关闭所有Activity
                 */

                SpUtils.clearSave(IconSettingActivity.this,"admin");//将保存的用户数据设空,下次将重新登录

                AppManager.getInstance().removeAllActivity();

                startActivity(new Intent(this, LoginActivity.class));

                break;
        }
    }

    /**
     *
     自定义CropCircleTransformation
     */
    class MyCropCircleTransformation implements Transformation {

        @Override
        public Bitmap transform(Bitmap source) {
            Bitmap bitmap = BitmapUtils.getBitmap(source);
            return bitmap;
        }

        @Override
        public String key() {
            return "CropCircleTransformation()";
        }
    }
}
