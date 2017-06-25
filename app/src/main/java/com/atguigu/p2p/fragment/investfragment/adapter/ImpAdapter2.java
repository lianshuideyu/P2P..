package com.atguigu.p2p.fragment.investfragment.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.atguigu.p2p.R;
import com.atguigu.p2p.bean.InvestProductBean;
import com.atguigu.p2p.view.ProgressView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/6/25.
 */

public class ImpAdapter2 extends ProductAdapter2<InvestProductBean.DataBean> {


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

    public ImpAdapter2(Context context, List<InvestProductBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    public void setData(InvestProductBean.DataBean dataBean) {

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

        View view = View.inflate(context, R.layout.fragment_investall_item, null);

        ButterKnife.inject(this,view);
        return view;
    }
}
