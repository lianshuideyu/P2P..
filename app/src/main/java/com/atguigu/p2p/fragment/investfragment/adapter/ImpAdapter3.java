package com.atguigu.p2p.fragment.investfragment.adapter;

import android.content.Context;

import com.atguigu.p2p.bean.InvestProductBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/25.
 */

public class ImpAdapter3 extends ProductAdapter3<InvestProductBean.DataBean> {

    public ImpAdapter3(Context context, List<InvestProductBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    public BaseHolder getViewHolder(InvestProductBean.DataBean dataBean) {

        return new ImpHolder3();
    }
}
