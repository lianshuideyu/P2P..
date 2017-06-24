package com.atguigu.p2p.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atguigu.p2p.ui.LoadingPager;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/21.
 */

public abstract class BaseFragment extends Fragment {

    public LoadingPager loadingPager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        loadingPager = new LoadingPager(getActivity()) {

            @Override
            public String getUrl() {
                return getChildUrl();
            }

            @Override
            public View getView() {
                View view = View.inflate(getContext(),setLayoutId(),null);

                ButterKnife.inject(BaseFragment.this,view);
                return view;
            }

            @Override
            protected void setResult(View sucessView, String json) {
//                ButterKnife.inject(BaseFragment.this,sucessView);
//initData("");//此处应该传入json数据， 先传一个假数据

                initData(json);
                Log.e("TAG","baseFragment==json==" + json);
            }

        };

        if(setLayoutId() == 0) {
            TextView textView = new TextView(getActivity());
            textView.setText("布局文件不能为空");
            return textView;
        }

        //View view = View.inflate(getActivity(),setLayoutId(),null);


        return loadingPager;
    }

    protected abstract void initData(String json);

    protected abstract String getChildUrl();

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
        loadingPager.loadData();//联网
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        ButterKnife.reset(this);
    }
}
