package com.atguigu.p2p.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.atguigu.p2p.R;
import com.atguigu.p2p.common.AppManager;
import com.atguigu.p2p.fragment.HomeFragment;
import com.atguigu.p2p.fragment.InvestFragment;
import com.atguigu.p2p.fragment.MoreFragment;
import com.atguigu.p2p.fragment.PropertyFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.main_rg)
    RadioGroup mainRg;

    private List<Fragment> fragments;
    private int position;

    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        //添加到Activity的管理栈
        AppManager.getInstance().addActivity(this);

        initFragment();

        initListener();
    }

    private void initListener() {
        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                switch (checkId) {
                    case R.id.rb_main :
                        position = 0;
                        break;
                    case R.id.rb_invest :
                        position = 1;
                        break;
                    case R.id.rb_propert :
                        position = 2;
                        break;
                    case R.id.rb_more :
                        position = 3;
                        break;
                }

                Fragment currentFragment = fragments.get(position);
                switchFragment(currentFragment);
            }
        });

    }

    /**
     * 切换页面
     * @param currentFragment
     */
    private void switchFragment(Fragment currentFragment) {
        //事务管理并开启
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if(tempFragment != currentFragment) {

            //先隐藏
            if(tempFragment != null) {
                ft.hide(tempFragment);
            }


            if(!currentFragment.isAdded()) {
                //如果不存在则创建
                ft.add(R.id.main_fl,currentFragment);

            }else {
                //如果存在则显示之前缓存的
                ft.show(currentFragment);
            }


            ft.commit();

            tempFragment = currentFragment;
        }//如果tempFragment与currentFragment相等不做任何操作

    }

    private void initFragment() {
        fragments = new ArrayList<>();

        fragments.add(new HomeFragment());
        fragments.add(new InvestFragment());
        fragments.add(new PropertyFragment());
        fragments.add(new MoreFragment());

        //默认显示主页面
        switchFragment(fragments.get(0));
    }

    /**
     * 设置2秒内连续点击2次才可退出程序
     * @param keyCode
     * @param event
     * @return
     */
    private boolean isExit = false;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {//点击返回键

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                    //Log.e("TAG","2秒后执行没有");
                }
            },2000);

            if(!isExit) {
                isExit = true;
                Toast.makeText(MainActivity.this, "残忍离开", Toast.LENGTH_SHORT).show();
                return true;
            }
        }

        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //推出的时候关闭移除
        AppManager.getInstance().removeActivity(this);
    }
}
