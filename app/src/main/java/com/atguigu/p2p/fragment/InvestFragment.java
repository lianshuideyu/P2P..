package com.atguigu.p2p.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.atguigu.p2p.R;
import com.atguigu.p2p.base.BaseFragment;
import com.atguigu.p2p.fragment.investfragment.InvestAllFragment;
import com.atguigu.p2p.fragment.investfragment.InvestHotFragment;
import com.atguigu.p2p.fragment.investfragment.InvestRecFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by Administrator on 2017/6/20.
 */

public class InvestFragment extends BaseFragment {


    @InjectView(R.id.tv_invest_all)
    TextView tvInvestAll;
    @InjectView(R.id.tv_invest_recommond)
    TextView tvInvestRecommond;
    @InjectView(R.id.tv_invest_hot)
    TextView tvInvestHot;
    @InjectView(R.id.vp_invest)
    ViewPager vpInvest;

    @Override
    protected void initData(String json) {

    }

    @Override
    protected String getChildUrl() {
        return "";
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_invest;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initData() {
        //添加不同页面数据
        initFragment();

        //设置viewpager数据
        vpInvest.setAdapter(new MyViewPagerAdapter(getFragmentManager()));


    }

    private List<BaseFragment> fragmentList;
    private void initFragment() {
        fragmentList = new ArrayList<>();

        fragmentList.add(new InvestAllFragment());
        fragmentList.add(new InvestRecFragment());
        fragmentList.add(new InvestHotFragment());

    }

    class MyViewPagerAdapter extends FragmentPagerAdapter {

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);


        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
