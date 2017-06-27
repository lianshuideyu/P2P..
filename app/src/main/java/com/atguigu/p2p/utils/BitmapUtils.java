package com.atguigu.p2p.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

/**
 * Created by Administrator on 2017/6/26.
 */

public class BitmapUtils {

    public static Bitmap getBitmap(Bitmap source) {
        //获取矩形图片最小的边
        int size = Math.min(source.getWidth(), source.getHeight());

        int width = (source.getWidth() - size) / 2;
        int height = (source.getHeight() - size) / 2;

        //根据矩形图片最小的边生成一个正方形图片
        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        //BitmapShader的作用是使用特定的图片来作为纹理来使用
        /*
        CLMP 如果需要填充的内容大小超过了bitmap size 就选bitmap 边界的颜色进行扩展

        REPEAT重复，不断的重复bitmap去填满，如果绘制的区域大于纹理图片的话，
              纹理图片会在这片区域不断重复

        MIRROR镜像的去填满。如果绘制的区域大于纹理图片的话，纹理图片会以镜像的形式重复出现

        */
        BitmapShader shader =
                new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        if (width != 0 || height != 0) {
            // source isn't square, move viewport to center
            Matrix matrix = new Matrix();
//            matrix.setTranslate(-width, -height);
            //在这调整头像被截取的位置，向右向下偏移
//            matrix.setTranslate(width + UIUtils.dp2px(10), height + UIUtils.dp2px(10));
            matrix.setTranslate(-width, -height);
            shader.setLocalMatrix(matrix);//利用矩阵进行定位
        }
        paint.setShader(shader);
        paint.setAntiAlias(true);

        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);

        source.recycle();

        return bitmap;

    }
}
