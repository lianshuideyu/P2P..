package com.atguigu.p2p.fragment.investfragment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atguigu.p2p.R;
import com.atguigu.p2p.bean.InvestProductBean;
import com.atguigu.p2p.view.ProgressView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/6/24.
 */

public class InvestAllAdapter extends BaseAdapter {

    private final List<InvestProductBean.DataBean> datas;
    private final Context context;

    public InvestAllAdapter(Context context, List<InvestProductBean.DataBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public InvestProductBean.DataBean getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View converView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (converView == null) {
            converView = View.inflate(context, R.layout.fragment_investall_item, null);

            viewHolder = new ViewHolder(converView);

            converView.setTag(viewHolder);
        }else {

            viewHolder = (ViewHolder) converView.getTag();

        }

        InvestProductBean.DataBean dataBean = datas.get(position);
        viewHolder.pName.setText(dataBean.getName());
        viewHolder.pMoney.setText(dataBean.getMoney());
        viewHolder.pYearlv.setText(dataBean.getYearRate());
        viewHolder.pSuodingdays.setText(dataBean.getSuodingDays());
        viewHolder.pMinzouzi.setText(dataBean.getMinTouMoney());
        viewHolder.pMinnum.setText(dataBean.getMemberNum());
        viewHolder.pProgresss.setProgress(Integer.parseInt(dataBean.getProgress()));

        return converView;
    }

    static class ViewHolder {
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

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
