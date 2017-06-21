package com.atguigu.p2p.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.atguigu.p2p.R;
import com.atguigu.p2p.common.MyApplication;
import com.atguigu.p2p.utils.UIUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/20.
 */

public class HomeFragment extends Fragment {

    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;

    //ImageView baseBack;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return initView();
    }

    private View initView() {

        View view = UIUtils.findViewById(R.layout.fragment_home);

        ButterKnife.inject(this,view);
//        baseBack = (ImageView) view.findViewById(R.id.base_back);
//
//        baseBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getContext(), "返回", Toast.LENGTH_SHORT).show();
//            }
//        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
    }

    /**
     * 添加数据
     */
    private void initData() {
        //测试异常
        //int i = 1 / 0;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.base_back, R.id.base_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.base_back:
                Toast.makeText(MyApplication.getContext(), "返回", Toast.LENGTH_SHORT).show();
                break;
            case R.id.base_setting:
                Toast.makeText(MyApplication.getContext(), "设置", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
