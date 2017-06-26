package com.atguigu.p2p.fragment;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.p2p.R;
import com.atguigu.p2p.base.BaseFragment;
import com.atguigu.p2p.common.AppNetConfig;
import com.atguigu.p2p.utils.BitmapUtils;
import com.atguigu.p2p.utils.SpUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

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
                SpUtils.saveString(getActivity(),"admin","");
                Toast.makeText(getContext(), "退出登录", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void initData() {
        //从保存的用户数据中得到数据并设置到布局上


        //将头像设置为圆形
        Picasso.with(getActivity())
                .load(AppNetConfig.BASE_URL+"images/tx.png")
                //.transform(new CropCircleTransformation())//加载圆形图片，transform可以设置多个
                .transform(new MyCropCircleTransformation())
                .into(ivMeIcon);

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
}
