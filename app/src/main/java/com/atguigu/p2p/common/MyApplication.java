package com.atguigu.p2p.common;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.widget.GFImageView;

/**
 * Created by Administrator on 2017/6/20.
 */

public class MyApplication extends Application {

    private static Context context;

    private static Thread mainThread;
    private static int  threadid;
    private static Handler handler;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;

        //初始化Grallery更改头像相关（打开相机，本地图库）
        initGralley();


        //初始化异常处理，暂时先注掉，方便处理异常
        //CrashHandler.getInstance().init(this);


        threadid = android.os.Process.myPid();
        handler = new Handler();
    }



    public static Thread getMainThread() {
        return mainThread;
    }

    public static int getThreadid() {
        return threadid;
    }


    public static Handler getHandler() {
        return handler;
    }

    private void initGralley() {
        //设置主题
        //ThemeConfig.CYAN
        ThemeConfig theme = new ThemeConfig.Builder()

        .build();
        //配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
        .build();

        //配置imageloader
        ImageLoader imageloader = new UILImageLoader();
        CoreConfig coreConfig = new CoreConfig.Builder(this, imageloader, theme)
                //.setDebug(BuildConfig.DEBUG)
                .setFunctionConfig(functionConfig)

        .build();
        GalleryFinal.init(coreConfig);
    }

    class UILImageLoader implements ImageLoader {


        private Bitmap.Config mConfig;

        public UILImageLoader() {
            this(Bitmap.Config.RGB_565);
        }

        public UILImageLoader(Bitmap.Config config) {
            this.mConfig = config;
        }

        @Override
        public void displayImage(Activity activity, String path, GFImageView imageView, Drawable defaultDrawable, int width, int height) {
            Picasso.with(activity)
                    .load(new File(path))
                    .placeholder(defaultDrawable)
                    .error(defaultDrawable)
                    .config(mConfig)
                    .resize(width, height)
                    .centerInside()
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .into(imageView);
        }

        @Override
        public void clearMemoryCache() {
        }
    }
}
