package com.atguigu.p2p.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.p2p.R;
import com.atguigu.p2p.base.BaseActivity;

import butterknife.InjectView;
import butterknife.OnClick;

public class WithDrawActivity extends BaseActivity {


    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;
    @InjectView(R.id.account_zhifubao)
    TextView accountZhifubao;
    @InjectView(R.id.select_bank)
    RelativeLayout selectBank;
    @InjectView(R.id.chongzhi_text)
    TextView chongzhiText;
    @InjectView(R.id.view)
    View view;
    @InjectView(R.id.et_input_money)
    EditText etInputMoney;
    @InjectView(R.id.chongzhi_text2)
    TextView chongzhiText2;
    @InjectView(R.id.textView5)
    TextView textView5;
    @InjectView(R.id.btn_tixian)
    Button btnTixian;

    @Override
    protected void initListener() {
        //监听edtiText的变化
        etInputMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!TextUtils.isEmpty(editable)) {
                    btnTixian.setEnabled(true);
                    btnTixian.setBackgroundResource(R.drawable.btn_01);
                }else {
                    btnTixian.setEnabled(false);
                    btnTixian.setBackgroundResource(R.drawable.btn_02);
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initTitle() {
        super.initTitle();
        baseTitle.setText("余额提现");
    }

    @Override
    protected void initView() {
        baseTitle.setVisibility(View.VISIBLE);

        btnTixian.setEnabled(false);//设置充值按键默认不可点击
    }

    @Override
    public int getLayout() {
        return R.layout.activity_with_draw;
    }


    @OnClick({R.id.base_back, R.id.btn_tixian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.base_back:
                finish();
                break;
            case R.id.btn_tixian:
                //点击提现
                showToast("提现");
                break;
        }
    }
}
