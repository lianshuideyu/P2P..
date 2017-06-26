package com.atguigu.p2p.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.atguigu.p2p.R;
import com.atguigu.p2p.base.BaseActivity;
import com.atguigu.p2p.bean.LoginBean;
import com.atguigu.p2p.common.AppNetConfig;
import com.atguigu.p2p.utils.HttpUtils;
import com.atguigu.p2p.utils.SpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.InjectView;

public class LoginActivity extends BaseActivity {

    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;
    @InjectView(R.id.tv_login_number)
    TextView tvLoginNumber;
    @InjectView(R.id.et_login_number)
    EditText etLoginNumber;
    @InjectView(R.id.rl_login)
    RelativeLayout rlLogin;
    @InjectView(R.id.tv_login_pwd)
    TextView tvLoginPwd;
    @InjectView(R.id.et_login_pwd)
    EditText etLoginPwd;
    @InjectView(R.id.btn_login)
    Button btnLogin;
    @InjectView(R.id.regitster_tv)
    TextView regitsterTv;

    @Override
    protected void initListener() {
        //点击登录
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //先判断用户名和密码是否为空
                String number = etLoginNumber.getText().toString().trim();
                String psd = etLoginPwd.getText().toString().trim();

                if(TextUtils.isEmpty(number) || TextUtils.isEmpty(psd)) {
                    showToast("用户不能为空");

                }else {
                    //联网登录
                    netLogin(number,psd);


                }

            }
        });

        //点击返回按键
        baseBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        //点击注册按键
        regitsterTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etLoginNumber.setText("");
                etLoginPwd.setText("");
                //调转到注册页面
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);


            }
        });
    }

    /*
    * 面试题
    * sp可以存哪些类型的数据？
    * sp存的数据最大是多少？
    * */
    private void netLogin(String number, String psd) {
        Map<String, String> map = new HashMap<>();

        map.put("phone",number);
        map.put("password",psd);
        //15321970103
        HttpUtils.getInstance().post(AppNetConfig.LOGIN, map, new HttpUtils.MyHttpClickListener() {
            @Override
            public void onSuccess(String content) {
                Log.e("TAG","LoginActivity联网成功==" + content);

                //先解析一下是否登录成功
                try {
                    JSONObject jsonObject = new JSONObject(content);
                    String isSuccess = jsonObject.getString("success");
                    Log.e("TAG","LoginActivity是否登录成功==" + isSuccess);
                    if(Boolean.parseBoolean(isSuccess)) {
                        //解析数据
                        //processData(content);
                        showToast("登录成功");

                        //保存数据到本地,保存的是json字符串
                        SpUtils.saveString(LoginActivity.this,"admin",content);

                        //启动mainActivity
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        showToast("账号或密码不对");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(String content) {
                Log.e("TAG","LoginActivity联网失败==" + content);
            }
        });

    }

    private void processData(String json) {

        LoginBean loginBean = JSON.parseObject(json, LoginBean.class);

        Log.e("TAG","log解析数据==" + loginBean.getData().getName());

    }


    @Override
    public void initTitle() {
        super.initTitle();

        baseTitle.setText("请登录");
    }


    @Override
    protected void initData() {

    }



    @Override
    protected void initView() {
        baseBack.setVisibility(View.VISIBLE);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }


}
