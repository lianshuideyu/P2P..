package com.atguigu.p2p.fragment;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.atguigu.p2p.R;
import com.atguigu.p2p.base.BaseFragment;
import com.atguigu.p2p.fragment.investfragment.fragment.InvestAllFragment;
import com.atguigu.p2p.fragment.investfragment.fragment.InvestHotFragment;
import com.atguigu.p2p.fragment.investfragment.fragment.InvestRecFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by Administrator on 2017/6/20.
 */

public class InvestFragment extends BaseFragment implements View.OnClickListener {


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

        //vpInvest.setCurrentItem(0);//默认选中全部理财页面
        selectText(tvInvestAll);
    }

    @Override
    public void initListener() {
        super.initListener();

        /**
         * viewpager 的监听事件
         */
        vpInvest.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0 :
                        selectText(tvInvestAll);
                        break;
                    case 1 :
                        selectText(tvInvestRecommond);
                        break;
                    case 2 :
                        selectText(tvInvestHot);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        /**
         * viewpager指针的点击事件
         *
         */
        tvInvestAll.setOnClickListener(this);
        tvInvestRecommond.setOnClickListener(this);
        tvInvestHot.setOnClickListener(this);


    }

    //选择不同的viewpager的指针
    private void selectText(View view) {
        defaultText(tvInvestAll);
        defaultText(tvInvestRecommond);
        defaultText(tvInvestHot);

        TextView textView = (TextView) view;
        textView.setTextColor(Color.WHITE);
        textView.setBackgroundColor(Color.parseColor("#5BC9F9"));

    }
    private void defaultText(View view) {
        TextView textView = (TextView) view;
        textView.setTextColor(Color.BLACK);
        textView.setBackgroundColor(Color.WHITE);
    }

    /**
     * 指针的点击事件
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_invest_all :
                vpInvest.setCurrentItem(0);
                break;
            case R.id.tv_invest_recommond :
                vpInvest.setCurrentItem(1);
                break;
            case R.id.tv_invest_hot :
                vpInvest.setCurrentItem(2);
                break;
        }

    }


    /*
    *
    * FragmentPagerAdapter
    * FragmentStatePagerAdapter
    * 面试题这两个有什么区别？
    * * FragmentPagerAdapter:该类内的每一个生成的 Fragment 都将保存在内存之中，
    * 因此适用于那些`相对静态的页，
    * 数量也比较少`的那种；如果需要处理有很多页，并且数据动态性较大、占用内存较多的情况，
    * 应该使用FragmentStatePagerAdapter

    * FragmentStatePagerAdapter:该 PagerAdapter 的实现将只保留当前页面，
    * 当页面离开视线后，就会被消除，释放其资源；而在页面需要显示时，
    * 生成新的页面(就像 ListView 的实现一样)。这么实现的好处就是当拥有大量的页面时，
    * 不必在内存中占用大量的内存。
    *
    * */
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
