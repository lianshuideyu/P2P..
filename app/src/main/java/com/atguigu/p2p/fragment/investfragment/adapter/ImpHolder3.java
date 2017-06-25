package com.atguigu.p2p.fragment.investfragment.adapter;

import android.view.View;
import android.widget.TextView;

import com.atguigu.p2p.R;
import com.atguigu.p2p.bean.InvestProductBean;
import com.atguigu.p2p.common.MyApplication;
import com.atguigu.p2p.view.ProgressView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/6/25.
 */

public class ImpHolder3 extends BaseHolder<InvestProductBean.DataBean> {

    @InjectView(R.id.p_name)
    TextView pName;
    @InjectView(R.id.p_money)
    TextView pMoney;
    @InjectView(R.id.p_yearlv)
    TextView pYearlv;
    @InjectView(R.id.p_suodingdays)
    TextView pSuodingdays;
    @InjectView(R.id.p_minzouzi)
    TextView pMinzouzi;
    @InjectView(R.id.p_minnum)
    TextView pMinnum;
    @InjectView(R.id.p_progresss)
    ProgressView pProgresss;

    public ImpHolder3(InvestProductBean.DataBean dataBean) {
        super(dataBean);
    }

    @Override
    protected void setData(InvestProductBean.DataBean dataBean) {
        pName.setText(dataBean.getName());
        pMoney.setText(dataBean.getMoney());
        pYearlv.setText(dataBean.getYearRate());
        pSuodingdays.setText(dataBean.getSuodingDays());
        pMinzouzi.setText(dataBean.getMinTouMoney());
        pMinnum.setText(dataBean.getMemberNum());
        pProgresss.setProgress(Integer.parseInt(dataBean.getProgress()));

    }

    @Override
    public View initView() {
        View view = View.inflate(MyApplication.getContext(), R.layout.fragment_investall_item, null);
        ButterKnife.inject(this, view);

        return view;
    }
}
