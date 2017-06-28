package com.atguigu.p2p.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.p2p.R;
import com.atguigu.p2p.base.BaseActivity;
import com.atguigu.p2p.bean.LoginBean;
import com.atguigu.p2p.bean.UpdateBean;
import com.atguigu.p2p.common.AppNetConfig;
import com.atguigu.p2p.common.ThreadManager;
import com.atguigu.p2p.utils.HttpUtils;
import com.atguigu.p2p.utils.SpUtils;
import com.atguigu.p2p.utils.UIUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.InjectView;

import static com.atguigu.p2p.R.id.splash_tv_version;

public class SplashActivity extends BaseActivity {


    @InjectView(splash_tv_version)
    TextView splashTvVersion;
    @InjectView(R.id.activity_splash)
    RelativeLayout activity_splash;

    @Override
    protected void initListener() {

    }


    @Override
    public int getLayout() {
        return R.layout.activity_splash;
    }

    private String getVersion() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            //给用户看的
            String versionName = packageInfo.versionName;

            //versionCode应用市场用来区分版本有没有更新
            int versionCode = packageInfo.versionCode;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return "";
    }

    @Override
    protected void initData() {
        /**
         *  动画效果的设置
         */
        final AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(1500);

        alphaAnimation.setFillAfter(true);
        activity_splash.startAnimation(alphaAnimation);

        //动画的监听
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (isHaveNet()) {
                    //弹出更新对话框
                    updateFromNet();

                    //动画播放结束进入主主页面
                    //enterActivity();

                    //清除动画
                    activity_splash.clearAnimation();

                    //finish();
                } else {
                    //手机没有网络，退出应用或进行其他操作
                    enterActivity();//这里选择进入主页面，一般会退出...
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 判断是否有网络连接
     *
     * @return
     */
    private boolean isHaveNet() {
        boolean connected = false;
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null) {
            connected = networkInfo.isConnected();
        }
        return connected;
    }

    /**
     * 显示更新对话框
     *
     * @param updateBean
     */
    private void showUpdateDialog(UpdateBean updateBean) {
        final String updateUrl = "http://47.93.118.241:8081/P2PInvest/app_new.apk";
        new AlertDialog.Builder(this)
                .setTitle("更新到最新版本")
                .setMessage("升级为：" + updateBean.getVersion() + "    " + updateBean.getDesc())
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //联网更新app,开始下载升级

                        downLoad(updateUrl);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //
                        enterActivity();

                        finish();//结束当广告页
                    }
                })
                .show();

    }

    /**
     * 真正开始联网下载新版本app
     *
     * @param updateUrl
     */
    private void downLoad(final String updateUrl) {
        //显示水平进度条
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.show();

/*
getCacheDir()方法用于获取/data/data/<application package>/cache目录

getFilesDir()方法用于获取/data/data/<application package>/files目录

通过Context.getExternalFilesDir()方法可以获取到 SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据

通过Context.getExternalCacheDir()方法可以获取到 SDCard/android/data/你的应用包名/cache/目录，一般存放临时缓存数据
*
* */
        final File file;
        //创建apk下载后要保存的路径
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //正常状态

            file = getExternalFilesDir("");//保存在外部路径

        } else {
            file = getFilesDir();//存在内部文件路径
        }


        //联网在子线程进行
        ThreadManager.getInstance().getThread().execute(new Runnable() {
            @Override
            public void run() {
                InputStream is = null;
                FileOutputStream fos = null;
                try {
                    URL url = new URL(updateUrl);
                    //得到连接对象
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);

                    //连接服务器
                    connection.connect();
                    //得到响应码
                    int code = connection.getResponseCode();
                    if (code == 200) {
                        //请求成功
                        Log.e("update", "请求升级网络成功");
                        is = connection.getInputStream();
                        //得到apk 的大小
                        int contentLength = connection.getContentLength();
                        dialog.setMax(contentLength);

                        File file1 = new File(file, "update.apk");

                        fos = new FileOutputStream(file1);

                        byte[] buffer = new byte[8192];
                        int length;

                        //显示水平进度条更新进度

                        while ((length = is.read(buffer)) != -1) {
                            fos.write(buffer, 0, length);//保存apk
                            //更新进度条
                            dialog.incrementProgressBy(length);
                        }

                        dialog.dismiss();//此时下载完成
                        //安装应用,弹出是否安装程序，调用意图

                        Intent install = new Intent(Intent.ACTION_INSTALL_PACKAGE);
                        install.setData(Uri.parse("file:" + file1.getAbsolutePath()));
                        startActivity(install);

                        finish();
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    //关流

                    try {
                        if (fos != null) {
                            fos.close();
                        }

                        if(is != null) {
                            is.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    /**
     * 联网更新app
     */
    private void updateFromNet() {
        //当需要更新的时候再弹出跟新的dialog
        //先联网获取数据，根据数据判断是否有新版本app，再确定是否需要升级
        HttpUtils.getInstance().get(AppNetConfig.UPDATE, new HttpUtils.MyHttpClickListener() {
            @Override
            public void onSuccess(String content) {
                Log.e("update", "升级联网==" + content);

                //解析数据
                if (content.indexOf("title") > 0) {
                    //联网失败，解析到H5页面,就直接进入主界面
                    enterActivity();

                } else if (!TextUtils.isEmpty(content)) {

                    UpdateBean updateBean = JSON.parseObject(content, UpdateBean.class);
                    String updateVersion = updateBean.getVersion();
                    //判断当前版本是否过时
                    if (!updateVersion.equals(getVersion())) {

                        showUpdateDialog(updateBean);
                    } else {
                        enterActivity();
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(String content) {
                Log.e("update", "升级失败==" + content);
            }
        });

    }


    /**
     * 进入主界面或登录页面
     */
    private void enterActivity() {
        if (isLogin()) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            //进入注册页面
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void initView() {
        //设置版本号
        splashTvVersion.setText(
                UIUtils.getVersionFromat(
                        R.string.verson_name, getVersion()
                )
        );
    }


    /**
     * 判断是否为第一次登录
     *
     * @return
     */
    private boolean isLogin() {
        String admin = SpUtils.getSave(this, "admin");
        Log.e("TAG", "log拿到存储数据==" + admin);
        if (TextUtils.isEmpty(admin)) {
            return false;
        }
        LoginBean loginBean = JSONObject.parseObject(admin, LoginBean.class);
        if (loginBean.isSuccess()) {
            return true;
        } else {
            return false;
        }

    }

}
