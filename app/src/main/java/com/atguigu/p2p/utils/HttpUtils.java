package com.atguigu.p2p.utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * Created by Administrator on 2017/6/21.
 */

public class HttpUtils {

    private final AsyncHttpClient client;

    private HttpUtils() {
       client = new AsyncHttpClient();
    }

    private static HttpUtils httpUtils = new HttpUtils();

    public static HttpUtils getInstance(){
        return httpUtils;
    }

    /*
        封装第三方联网框架
        * get联网请求
        * */
    public void get(String url, final MyHttpClickListener httpListener){



        client.get(url, new AsyncHttpResponseHandler() {
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
        });

    }

    /**
     * 利用接口将联网数据回传回去
     */
    public interface MyHttpClickListener{

        void onSuccess(String content);

        void onFailure(String content);

    }

}
