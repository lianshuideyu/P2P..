package com.atguigu.p2p.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.p2p.R;
import com.atguigu.p2p.base.BaseActivity;
import com.atguigu.p2p.common.AppManager;
import com.atguigu.p2p.common.AppNetConfig;
import com.atguigu.p2p.utils.BitmapUtils;
import com.atguigu.p2p.utils.SpUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

public class IconSettingActivity extends BaseActivity {


    public static final int REQUEST_CODE_CAMERA = 0;
    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;
    @InjectView(R.id.iv_user_icon)
    ImageView ivUserIcon;
    @InjectView(R.id.tv_user_change)
    TextView tvUserChange;
    @InjectView(R.id.btn_user_logout)
    Button btnUserLogout;

    @Override
    public int getLayout() {
        return R.layout.activity_icon_setting;
    }

    @Override
    protected void initView() {
        baseBack.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        baseTitle.setText("用户设置");

        //正常情况应该从缓存中获取用户数据然后加载保存的头像链接，这里简单写
        //设置头像
        //将头像设置为圆形
        Picasso.with(this)
                .load(AppNetConfig.BASE_URL + "images/tx.png")
                //.transform(new CropCircleTransformation())//加载圆形图片，transform可以设置多个
                .transform(new MyCropCircleTransformation())
                .into(ivUserIcon);
    }


    @OnClick({R.id.base_back, R.id.tv_user_change, R.id.btn_user_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.base_back:
                finish();
                break;
            case R.id.tv_user_change:
                //showToast("更换头像");

                showTheDialog();

                break;
            case R.id.btn_user_logout:
                //退出登录
                showToast("退出登录");
                /**
                 * 1.清除缓存（sp，file..）
                 * 2.跳转到登录页面
                 * 3.关闭所有Activity
                 */

                SpUtils.clearSave(IconSettingActivity.this, "admin");//将保存的用户数据设空,下次将重新登录

                AppManager.getInstance().removeAllActivity();

                startActivity(new Intent(this, LoginActivity.class));

                break;
        }
    }

    /**
     * 设置头像
     */
    private void showTheDialog() {

        String[] items = new String[]{"相机","本地相册"};
        new AlertDialog.Builder(this)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {

                        showToast(position + "");
                        switch (position) {
                            case 0 :
                                //打开相机
                                showCamera();

                                break;
                            case 1 :
                                //打开本地相册

                                showLocalPhoto();
                                break;
                        }
                    }
                })
                .show();

    }

    private void showLocalPhoto() {


    }

    /**
     * 打开相机设置头像用
     */
    private void showCamera() {

//带配置的设置
//        GalleryFinal.openCamera(REQUEST_CODE_CAMERA, functionConfig, mOnHanlderResultCallback);

        GalleryFinal.openCamera(REQUEST_CODE_CAMERA, new GalleryFinal.OnHanlderResultCallback() {
            @Override
            public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                Log.e("camera","相机开启");
                PhotoInfo photoInfo = resultList.get(0);
                //将拍照图片设置到头像
                Picasso.with(IconSettingActivity.this)
                        .load(new File(photoInfo.getPhotoPath()))//这里加载保存到本地的拍照图片
                        .transform(new MyCropCircleTransformation())
                        .into(ivUserIcon);
                Log.e("camera","相机==" + photoInfo.getPhotoPath());

                //然后上传图片到服务器

                //保存到sp
            }

            @Override
            public void onHanlderFailure(int requestCode, String errorMsg) {
                Log.e("camera","相机开启失败==" + errorMsg);
            }
        });
    }

    /**
     * 自定义CropCircleTransformation
     */
    class MyCropCircleTransformation implements Transformation {

        @Override
        public Bitmap transform(Bitmap source) {
            Bitmap bitmap = BitmapUtils.getBitmap(source);
            return bitmap;
        }

        @Override
        public String key() {
            return "CropCircleTransformation()";
        }
    }
}
