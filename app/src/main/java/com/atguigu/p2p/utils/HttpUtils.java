package com.atguigu.p2p.utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/21.
 */

public class HttpUtils {

    private AsyncHttpClient httpClient;

    private MyHttpClickListener httpListener;

    private HttpUtils() {
       httpClient = new AsyncHttpClient();
    }

    private static HttpUtils httpUtils = new HttpUtils();

    public static HttpUtils getInstance(){
        return httpUtils;
    }

    /*
        封装第三方联网框架
        * get联网请求
        * */
    public void get(String url,MyHttpClickListener httpListener){

        this.httpListener = httpListener;
        httpClient.get(url, handler);

    }

    AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int statusCode, String content) {
            super.onSuccess(statusCode, content);
            //Log.e("TAG", "HomeFragment联网成功==" + content);

            if(httpListener != null) {
                httpListener.onSuccess(content);

            }
        }

        @Override
        public void onFailure(Throwable error, String content) {
            super.onFailure(error, content);

            //Log.e("TAG", "HomeFragment联网失败==" + content);
            if(httpListener != null) {
                httpListener.onFailure(content);

            }
        }
    };
    /**
     * 利用接口将联网数据回传回去
     */
    public interface MyHttpClickListener{

        void onSuccess(String content);

        void onFailure(String content);

    }


    /*
       封装第三方联网框架
       * post联网请求
       * */
    public void post(String url, Map<String,String> map, MyHttpClickListener httpListener){

        this.httpListener = httpListener;
        RequestParams requestParams = new RequestParams(map);

//        Set<String> keys = map.keySet();
//        for (String key : keys) {
//            String value = map.get(key);
//            requestParams.put(key,value);
//        }


        httpClient.post(url,requestParams,handler);
//        httpClient.post(url,requestParams,new AsyncHttpResponseHandler(){
//            @Override
//            public void onSuccess(int statusCode, String content) {
//                super.onSuccess(statusCode, content);
//            }
//
//            @Override
//            public void onFailure(Throwable error, String content) {
//                super.onFailure(error, content);
//            }
//        });



    }
}
