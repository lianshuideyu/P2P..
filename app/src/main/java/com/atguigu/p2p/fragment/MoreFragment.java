package com.atguigu.p2p.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.atguigu.p2p.R;
import com.atguigu.p2p.activity.moregusturelock.GestureEditActivity;
import com.atguigu.p2p.activity.moregusturelock.GestureVerifyActivity;
import com.atguigu.p2p.base.BaseFragment;
import com.atguigu.p2p.utils.SpUtils;

import butterknife.InjectView;

/**
 * Created by Administrator on 2017/6/20.
 */

public class MoreFragment extends BaseFragment {

    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;
    @InjectView(R.id.tv_more_regist)
    TextView tvMoreRegist;
    @InjectView(R.id.toggle_more)
    ToggleButton toggleMore;
    @InjectView(R.id.tv_more_reset)
    TextView tvMoreReset;
    @InjectView(R.id.tv_more_phone)
    TextView tvMorePhone;
    @InjectView(R.id.rl_more_contact)
    RelativeLayout rlMoreContact;
    @InjectView(R.id.tv_more_fankui)
    TextView tvMoreFankui;
    @InjectView(R.id.tv_more_share)
    TextView tvMoreShare;
    @InjectView(R.id.tv_more_about)
    TextView tvMoreAbout;

    @Override
    protected void initData(String json) {

    }

    @Override
    protected String getChildUrl() {
        return "";
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_more;
    }

    @Override
    protected void initTitle() {
        baseTitle.setText("设置信息");
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initView() {
        super.initView();

        boolean toggleIscheck = SpUtils.getToggleIscheck(getActivity());
        if(toggleIscheck) {
            toggleMore.setChecked(true);
        }
    }

    @Override
    public void initListener() {
        super.initListener();

        toggleMore.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {

                if(isCheck) {
                    //打开手势识别//记录打开的状态
                    SpUtils.saveToggleIscheck(getActivity(),isCheck);

                    //如果是第一次打开就设置
                    Intent intent = new Intent(getActivity(), GestureVerifyActivity.class);
                    startActivity(intent);

                }else {
                    //关闭手势识别，记录关闭状态
                    SpUtils.saveToggleIscheck(getActivity(),isCheck);

                }
            }
        });

        //重置手势密码
        tvMoreReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean toggleIscheck = SpUtils.getToggleIscheck(getActivity());
                if(toggleIscheck) {
                    Intent intent = new Intent(getActivity(), GestureEditActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
