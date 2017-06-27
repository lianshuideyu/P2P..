package com.atguigu.p2p.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.atguigu.p2p.R;
import com.atguigu.p2p.activity.IconSettingActivity;
import com.atguigu.p2p.base.BaseFragment;
import com.atguigu.p2p.bean.LoginBean;
import com.atguigu.p2p.common.AppNetConfig;
import com.atguigu.p2p.utils.BitmapUtils;
import com.atguigu.p2p.utils.SpUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.UnsupportedEncodingException;

import butterknife.InjectView;

/**
 * Created by Administrator on 2017/6/20.
 */

public class PropertyFragment extends BaseFragment {

    @InjectView(R.id.tv_settings)
    TextView tvSettings;
    @InjectView(R.id.iv_me_icon)
    ImageView ivMeIcon;
    @InjectView(R.id.rl_me_icon)
    RelativeLayout rlMeIcon;
    @InjectView(R.id.tv_me_name)
    TextView tvMeName;
    @InjectView(R.id.rl_me)
    RelativeLayout rlMe;
    @InjectView(R.id.recharge)
    ImageView recharge;
    @InjectView(R.id.withdraw)
    ImageView withdraw;
    @InjectView(R.id.ll_touzi)
    TextView llTouzi;
    @InjectView(R.id.ll_touzi_zhiguan)
    TextView llTouziZhiguan;
    @InjectView(R.id.ll_zichan)
    TextView llZichan;

    @Override
    protected void initData(String json) {

    }

    @Override
    protected String getChildUrl() {
        return "";
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_property;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    public void initListener() {
        super.initListener();

        //暂时测试用，点击设置按键，退出登录
        tvSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), IconSettingActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void initData() {
        //从保存的用户数据中得到数据并设置到布局上
        String json = SpUtils.getSave(getContext(), "admin");
        if(!TextUtils.isEmpty(json)) {
            LoginBean loginBean = JSON.parseObject(json, LoginBean.class);
            //因为之前头像的服务器地址失效了在这里重新设置
            loginBean.getData().setImageurl(AppNetConfig.BASE_URL+"images/tx.png");

            //将头像设置为圆形
            Picasso.with(getActivity())
                    .load(loginBean.getData().getImageurl())
                    //.transform(new CropCircleTransformation())//加载圆形图片，transform可以设置多个
                    .transform(new MyCropCircleTransformation())
                    .into(ivMeIcon);

            String name = loginBean.getData().getName();
            try {
                String s = new String(name.getBytes(), "UTF-8");
                tvMeName.setText(s);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }/*else {

            //将头像设置为圆形
            Picasso.with(getActivity())
                    .load(R.drawable.my_user_default)
                    //.transform(new CropCircleTransformation())//加载圆形图片，transform可以设置多个
                    .into(ivMeIcon);
            tvMeName.setText("用户名");

        }*/


    }

    /**
     *
     自定义CropCircleTransformation
     */
    class MyCropCircleTransformation implements Transformation{

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

//    @Override
//    public void onResume() {
//        super.onResume();
//
//        initData();
//    }
}
