package com.atguigu.p2p.fragment;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.atguigu.p2p.R;
import com.atguigu.p2p.base.BaseFragment;
import com.atguigu.p2p.bean.IndexBean;
import com.atguigu.p2p.common.AppNetConfig;
import com.atguigu.p2p.utils.HttpUtils;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by Administrator on 2017/6/20.
 */

public class HomeFragment extends BaseFragment {

    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;

    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.banner)
    Banner banner;
    @InjectView(R.id.tv_home_product)
    TextView tvHomeProduct;
    @InjectView(R.id.tv_home_yearrate)
    TextView tvHomeYearrate;

    private IndexBean indexBean;

    //ImageView baseBack;


    @Override
    public int setLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        super.initView();

    }


    @Override
    protected void initTitle() {
        baseTitle.setText("首页");

    }

    /**
     * 添加数据
     */
    @Override
    protected void initData() {

        getDataFromNet();
    }


    /**
     * 联网请求数据
     */
    private void getDataFromNet() {

        HttpUtils.getInstance().get(AppNetConfig.INDEX, new HttpUtils.MyHttpClickListener() {
            @Override
            public void onSuccess(String content) {
                //联网成功
                Log.e("TAG", "HomeFragment联网成功==" + content);
                processData(content);
            }

            @Override
            public void onFailure(String content) {
                //联网失败
                Log.e("TAG", "HomeFragment联网失败==" + content);

            }
        });
    }

    private void processData(String json) {
        if (!TextUtils.isEmpty(json)) {
            indexBean = JSON.parseObject(json, IndexBean.class);

            //Log.e("TAG","HomeFragment解析数据成功==" + indexBean.getProInfo().getName());

            //添加banner数据
            //设置图片加载器
            initBanner();
        } else {

            Log.e("TAG", "HomeFragment解析没有数据");
        }

    }

    /**
     * 手动解析
     *
     * @param json
     */
    private void processData2(String json) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            //最外层对象
            indexBean = new IndexBean();
            //imageArr数组
            JSONArray imageArr = jsonObject.optJSONArray("imageArr");
            //新建集合准备放数据
            List<IndexBean.ImageArrBean> imageArrList = new ArrayList<>();

            for (int i = 0; i < imageArr.length(); i++) {
                IndexBean.ImageArrBean imageArrBean = new IndexBean.ImageArrBean();

                JSONObject imageArrJSONObject = imageArr.getJSONObject(i);

                String id = imageArrJSONObject.getString("ID");
                imageArrBean.setID(id);
                //Log.e("TAG","id==" + id);

                String imapaurl = imageArrJSONObject.getString("IMAPAURL");
                imageArrBean.setIMAPAURL(imapaurl);

                String imaurl = imageArrJSONObject.getString("IMAURL");
                imageArrBean.setIMAURL(imaurl);

                //将设置好数据的对象添加到集合中
                imageArrList.add(imageArrBean);
            }

            //将集合设置到最外层对象中
            indexBean.setImageArr(imageArrList);

            //解析proInfo对象
            IndexBean.ProInfoBean proInfoBean = new IndexBean.ProInfoBean();
            JSONObject jsonObject1 = jsonObject.optJSONObject("proInfo");

            String id = jsonObject1.getString("id");
            proInfoBean.setId(id);

            String memberNum = jsonObject1.getString("memberNum");
            proInfoBean.setMemberNum(memberNum);

            String minTouMoney = jsonObject1.getString("minTouMoney");
            proInfoBean.setMinTouMoney(minTouMoney);

            String money = jsonObject1.getString("money");
            proInfoBean.setMoney(money);

            String name = jsonObject1.getString("name");
            proInfoBean.setName(name);

            String progress = jsonObject1.getString("progress");
            proInfoBean.setProgress(progress);

            String suodingDays = jsonObject1.getString("suodingDays");
            proInfoBean.setSuodingDays(suodingDays);

            String yearRate = jsonObject1.getString("yearRate");
            proInfoBean.setYearRate(yearRate);


            indexBean.setProInfo(proInfoBean);

            //添加banner数据
            initBanner();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private List<String> images;

    private void initBanner() {
        List<IndexBean.ImageArrBean> imageArr = indexBean.getImageArr();

        if (imageArr != null && imageArr.size() > 0) {
            //Log.e("TAG","imaurl==" + imageArr.size());
            banner.setImageLoader(new GlideImageLoader());
            //设置图片集合
            images = new ArrayList<>();

            for (int i = 0; i < imageArr.size(); i++) {
                images.add(AppNetConfig.BASE_URL + imageArr.get(i).getIMAURL());
                //Log.e("TAG","imaurl==" + AppNetConfig.BASE_URL + imageArr.get(i).getIMAURL());

            }


            banner.setImages(images);
            //banner设置方法全部调用完毕时最后调用
            banner.start();
        }

    }


    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */

            //Picasso 加载图片简单用法
            Picasso.with(context).load((String) path).into(imageView);
        }
    }
}
