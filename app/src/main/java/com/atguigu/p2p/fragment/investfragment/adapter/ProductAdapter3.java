package com.atguigu.p2p.fragment.investfragment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/6/24.
 */

public abstract class ProductAdapter3<T> extends BaseAdapter {

    private final List<T> datas;
    private final Context context;

    public ProductAdapter3(Context context, List<T> datas) {
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

        BaseHolder viewHolder;
        if (converView == null) {
//            converView = View.inflate(context, R.layout.fragment_investall_item, null);

            viewHolder = getViewHolder(datas.get(position));

            //converView.setTag(viewHolder);
        }else {

            viewHolder = (BaseHolder) converView.getTag();

        }

        viewHolder.setData(datas.get(position));

        return viewHolder.getView();
    }

    public abstract BaseHolder getViewHolder(T t) ;

}
