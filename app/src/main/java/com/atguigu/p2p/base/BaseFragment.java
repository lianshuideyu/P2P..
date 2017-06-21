package com.atguigu.p2p.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/21.
 */

public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(setLayoutId() == 0) {
            TextView textView = new TextView(getActivity());
            textView.setText("布局文件不能为空");
            return textView;
        }
        View view = View.inflate(getActivity(),setLayoutId(),null);

        ButterKnife.inject(this,view);
        return view;
    }

    public abstract int setLayoutId();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

        initTitle();

        initData();

        initListener();

    }

    protected abstract void initTitle();

    public void initListener(){

    }

    protected abstract void initData();

    public void initView(){

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        ButterKnife.reset(this);
    }
}
