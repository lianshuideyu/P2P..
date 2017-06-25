package com.atguigu.p2p.fragment.investfragment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/6/24.
 */

public abstract class ProductAdapter2<T> extends BaseAdapter {

    public final List<T> datas;
    public final Context context;

    public ProductAdapter2(Context context, List<T> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public T getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View converView, ViewGroup viewGroup) {
         /*
        *
        * 优化三步
        * 第一步 抽出initView
        * 第二步 抽出setTag
        * 第三步 抽出设置数据
        *
        * */

        ViewHolder viewHolder;
        if (converView == null) {
            converView = initView();
                    //View.inflate(context, R.layout.fragment_investall_item, null);

            viewHolder = new ViewHolder(converView);

            //converView.setTag(viewHolder);
        }else {

            viewHolder = (ViewHolder) converView.getTag();

        }

//        InvestProductBean.DataBean dataBean =  datas.get(position);
//        viewHolder.pName.setText(dataBean.getName());
//        viewHolder.pMoney.setText(dataBean.getMoney());
//        viewHolder.pYearlv.setText(dataBean.getYearRate());
//        viewHolder.pSuodingdays.setText(dataBean.getSuodingDays());
//        viewHolder.pMinzouzi.setText(dataBean.getMinTouMoney());
//        viewHolder.pMinnum.setText(dataBean.getMemberNum());
//        viewHolder.pProgresss.setProgress(Integer.parseInt(dataBean.getProgress()));
        setData(datas.get(position));

        return converView;
    }

    public abstract void setData(T t);

    public abstract View initView();

    static class ViewHolder {
//        @InjectView(R.id.p_name)
//        TextView pName;
//        @InjectView(R.id.p_money)
//        TextView pMoney;
//        @InjectView(R.id.p_yearlv)
//        TextView pYearlv;
//        @InjectView(R.id.p_suodingdays)
//        TextView pSuodingdays;
//        @InjectView(R.id.p_minzouzi)
//        TextView pMinzouzi;
//        @InjectView(R.id.p_minnum)
//        TextView pMinnum;
//        @InjectView(R.id.p_progresss)
//        ProgressView pProgresss;

        ViewHolder(View view) {
//            ButterKnife.inject(this, view);
            view.setTag(this);
        }
    }
}
