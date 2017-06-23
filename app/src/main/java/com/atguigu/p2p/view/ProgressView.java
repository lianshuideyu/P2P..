package com.atguigu.p2p.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.atguigu.p2p.utils.UIUtils;

/**
 * Created by Administrator on 2017/6/23.
 */

public class ProgressView extends View {

    //环边 的宽度
    int strokeWidth ;

    int paintColor = Color.BLUE;

    int width ;
    int heigth;

    int progress = 60;
    int max = 100;

    private Paint paint;

    private float sweepAngle = 60;//圆弧进度的覆盖的角度

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

        paint.setAntiAlias(true);


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
        strokeWidth = UIUtils.dp2px(10);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(paintColor);
        int cx = width / 2;
        int cy = heigth / 2;

        int radius = cx - strokeWidth / 2;
        canvas.drawCircle(cx,cy,radius,paint);

        //画弧
        paint.setColor(Color.RED);
        RectF rectF = new RectF();
        rectF.set(strokeWidth/2,strokeWidth/2,width-strokeWidth/2,heigth-strokeWidth/2);
        canvas.drawArc(rectF,0,progress * 360 / max,false,paint);

        //画文本
        String text = progress * 100 / max + "%" ;
        paint.setStrokeWidth(0);
        paint.setTextSize(30);

        Rect rect = new Rect();
        paint.getTextBounds(text,0,text.length(),rect);

        int textWidth = rect.width();
        int textHeight = rect.height();
        float textX = width / 2 - textWidth/2;
        float textY = heigth / 2 + textHeight/2;

        canvas.drawText(text,textX,textY,paint);

    }
}
