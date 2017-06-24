package com.atguigu.p2p.fragment;

import com.atguigu.p2p.R;
import com.atguigu.p2p.base.BaseFragment;
import com.atguigu.p2p.common.AppNetConfig;

/**
 * Created by Administrator on 2017/6/20.
 */

public class PropertyFragment extends BaseFragment {

    @Override
    protected void initData(String json) {

    }

    @Override
    protected String getChildUrl() {
        return  AppNetConfig.INDEX;
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initData() {

    }
}
