package com.atguigu.p2p.fragment.investfragment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/6/24.
 */

public abstract class ProductAdapter1<T> extends BaseAdapter {

    public final List<T> datas;
    public final Context context;

    public ProductAdapter1(Context context, List<T> datas) {
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

        return getItemView(position, converView, viewGroup,datas);
    }

    public abstract View getItemView(int position, View converView, ViewGroup viewGroup, List<T> datas) ;

//    static class ViewHolder {
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
//
//        ViewHolder(View view) {
//            ButterKnife.inject(this, view);
//        }
//    }
}
