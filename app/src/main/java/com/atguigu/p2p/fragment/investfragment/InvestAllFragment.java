package com.atguigu.p2p.fragment.investfragment;

import android.util.Log;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.atguigu.p2p.R;
import com.atguigu.p2p.base.BaseFragment;
import com.atguigu.p2p.bean.InvestProductBean;
import com.atguigu.p2p.common.AppNetConfig;
import com.atguigu.p2p.fragment.investfragment.adapter.InvestAllAdapter;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by Administrator on 2017/6/20.
 */

public class InvestAllFragment extends BaseFragment {

    @InjectView(R.id.lv_invest_all)
    ListView lvInvestAll;

    private List<InvestProductBean.DataBean> datas;
    private InvestAllAdapter adapter;

    @Override
    protected void initData(String json) {

        //解析数据
        InvestProductBean productBean = JSON.parseObject(json, InvestProductBean.class);
        Log.e("TAG", "InvestAllFragment解析数据: " + productBean.getData().get(0).getName());
        datas = productBean.getData();

        if(datas != null && datas.size() > 0) {

            adapter = new InvestAllAdapter(getActivity(),datas);
            lvInvestAll.setAdapter(adapter);

        }
    }

    @Override
    protected String getChildUrl() {
        return AppNetConfig.PRODUCT;
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_investall;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initData() {

    }

}
