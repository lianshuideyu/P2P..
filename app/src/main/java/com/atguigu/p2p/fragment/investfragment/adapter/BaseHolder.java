package com.atguigu.p2p.fragment.investfragment.adapter;

import android.view.View;

/**
 * Created by Administrator on 2017/6/25.
 */

public abstract class BaseHolder<T> {

    private View rootView;

    public BaseHolder(T bean) {

        rootView = initView();

        rootView.setTag(this);

        setData(bean);
    }

    protected abstract void setData(T bean);

    public abstract View initView() ;

    public View getView() {

        return rootView;
    }
}
