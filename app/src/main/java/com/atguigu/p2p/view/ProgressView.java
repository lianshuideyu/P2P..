package com.atguigu.p2p.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.atguigu.p2p.utils.UIUtils;

/**
 * Created by Administrator on 2017/6/23.
 */

public class ProgressView extends View {

    //环边 的宽度
    int strokeWidth = UIUtils.dp2px(20);

    int paintColor = Color.BLUE;

    int width ;
    int heigth;

    private Paint paint;

    public ProgressView(Context context) {
        super(context);
        initPaint();
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPaint();
    }

    /**
     * 设置画笔
     */
    private void initPaint() {
        paint = new Paint();

        paint.setColor(paintColor);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(strokeWidth);

        /*
        * 三种样式
        * stroke 描边
        * fill 填充
        * stroke and fill 即填充又描边
        *
        * */
        paint.setStyle(Paint.Style.STROKE);
    }

    /**
     * 测量
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = getWidth();
        heigth = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*
        * 1.画描边
        * 2.画 弧
        * 3.画 文字
        * */
        //圆心
        int cx = width / 2;
        int cy = heigth / 2;

        int radius = cx - strokeWidth / 2;
        canvas.drawCircle(cx,cy,radius,paint);

    }
}
