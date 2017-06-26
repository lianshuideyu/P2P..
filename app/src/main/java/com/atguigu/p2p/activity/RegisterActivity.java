package com.atguigu.p2p.activity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.p2p.R;
import com.atguigu.p2p.base.BaseActivity;
import com.atguigu.p2p.common.AppNetConfig;
import com.atguigu.p2p.utils.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import butterknife.InjectView;

public class RegisterActivity extends BaseActivity {

    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;
    @InjectView(R.id.et_register_number)
    EditText etRegisterNumber;
    @InjectView(R.id.et_register_name)
    EditText etRegisterName;
    @InjectView(R.id.et_register_pwd)
    EditText etRegisterPwd;
    @InjectView(R.id.et_register_pwdagain)
    EditText etRegisterPwdagain;
    @InjectView(R.id.btn_register)
    Button btnRegister;

    @Override
    public int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        //返回按键可见
        baseBack.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initListener() {
        //点击返回
        baseBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //点击注册
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String numner = etRegisterNumber.getText().toString().trim();
                String name = etRegisterName.getText().toString().trim();
                String pwd = etRegisterPwd.getText().toString().trim();
                String pwd2 = etRegisterPwdagain.getText().toString().trim();

                if(TextUtils.isEmpty(numner) || TextUtils.isEmpty(name)||
                TextUtils.isEmpty(pwd) || TextUtils.isEmpty(pwd2) ) {

                    showToast("请补全信息");
                }else {
                    if(pwd.equals(pwd2)) {
                        //联网提交注册信息
//                        showToast("可以提交");
                        try {
                            String s = new String(name.getBytes(), "UTF-8");
                            registerForNet(numner,s,pwd);

                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                    }else {
                        showToast("请确认密码相同");
                    }

                }
            }
        });
    }

    //联网提交注册信息
    private void registerForNet(String numner, String name, String pwd) {

        Map<String, String> map = new HashMap<>();

        map.put("name",name);
        map.put("phone",numner);
        map.put("password",pwd);

        HttpUtils.getInstance().post(AppNetConfig.REGISTER, map, new HttpUtils.MyHttpClickListener() {
            @Override
            public void onSuccess(String content) {

                Log.e("TAG","注册联网成功==" + content);

                //然后解析判断是否注册成功
                try {
                    JSONObject jsonObject = new JSONObject(content);
                    boolean isExist = jsonObject.optBoolean("isExist");//为true是表示用户已存在
                    if(!isExist) {//为false时表示注册成功
                        //注册成功
                        showToast("注册成功,请重新登录");
                        finish();
                    }else {
                        showToast("用户已存在");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(String content) {
                Log.e("TAG","注册联网失败==" + content);
            }
        });

    }

    @Override
    protected void initData() {


    }

    @Override
    public void initTitle() {
        super.initTitle();
        baseTitle.setText("注册");
    }
}
