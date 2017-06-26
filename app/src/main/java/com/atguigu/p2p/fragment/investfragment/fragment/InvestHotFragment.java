package com.atguigu.p2p.fragment.investfragment.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.TextView;

import com.atguigu.p2p.R;
import com.atguigu.p2p.base.BaseFragment;
import com.atguigu.p2p.utils.UIUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Set;

import butterknife.InjectView;

/**
 * Created by Administrator on 2017/6/20.
 */

public class InvestHotFragment extends BaseFragment {

    @InjectView(R.id.id_flowlayout)
    TagFlowLayout idFlowlayout;

    private String[] datas = new String[]{"新手福利计划", "财神道90天计划", "硅谷计划", "30天理财计划", "180天理财计划", "月月升", "中情局投资商业经营", "大学老师购买车辆", "屌丝下海经商计划", "美人鱼影视拍摄投资", "Android培训老师自己周转", "养猪场扩大经营",
            "旅游公司扩大规模", "摩托罗拉洗钱计划", "铁路局回款计划", "屌丝迎娶白富美计划"
    };

    @Override
    protected void initData(String json) {

    }

    @Override
    protected String getChildUrl() {
        return "";
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_investhot;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initData() {

        //设置数据
        idFlowlayout.setAdapter(new TagAdapter<String>(datas) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = new TextView(getActivity());
                tv.setTextSize(20);
                tv.setText(s);
                //可以设置文字的颜色随机
                tv.setTextColor(Color.WHITE);
                Drawable drawable = getResources().getDrawable(R.drawable.hot_shape);
                tv.setBackgroundDrawable(drawable);
                //设置背景的颜色随机,只有GradientDrawable可以设置颜色
                GradientDrawable background = (GradientDrawable) tv.getBackground();
                background.setColor(UIUtils.getRandomColor());

                return tv;
            }
        });
    }

    @Override
    public void initView() {
        super.initView();

        //点击事件
        idFlowlayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {

//                getActivity().setTitle("choose:" + selectPosSet.toString());
            }
        });

    }
}
