package com.atguigu.p2p.fragment.investfragment.adapter;

import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/25.
 */

public abstract class BaseHolder<T> {

    private View rootView;
    private T t;

    public BaseHolder() {

        rootView = initView();
        ButterKnife.inject(this,rootView);

        rootView.setTag(this);

    }

    protected abstract void setChildData(T t);


    public abstract View initView() ;

    public View getView() {

        return rootView;
    }

    public void setData(T t) {
        this.t = t;
        setChildData(t);
    }
}
