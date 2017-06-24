package com.atguigu.p2p.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.atguigu.p2p.R;
import com.atguigu.p2p.utils.UIUtils;

/**
 * Created by Administrator on 2017/6/23.
 */

public class ProgressView extends View {

    //环边 的宽度
    int strokeWidth ;

    int paintColor ;

    int progress = 0;
    int max = 100;

    private Paint paint;
    int width ;
    int heigth;

    private float sweepAngle = 0;//圆弧进度的覆盖的角度

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public ProgressView(Context context) {
        super(context);
        initPaint();
    }


    /*
    * 自定义属性 三步
    * 第一步 创建attrs文件
    * 第二步 在自定义控件构造器方法中 实例化attrs对象并获取属性名称和默认值
    * 第三步 在布局文件中使用自定义的属性
    * */
    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPaint();

        //自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);

        paintColor = typedArray.getColor(R.styleable.ProgressView_paintColor,Color.BLACK);
        progress = typedArray.getInteger(R.styleable.ProgressView_progress,90);
        max = typedArray.getInteger(R.styleable.ProgressView_max,100);
        strokeWidth = (int) typedArray.getDimension(R.styleable.ProgressView_strokeWidth,15);


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

        width = getMeasuredWidth();
        heigth = getMeasuredHeight();
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

    /*
    * 面试题：
    * invalidate和postInvalidate的区别是什么
    * invalidate是主线程进行强制重绘
    * postInvalidate是分线程进行强制重绘
    * */

    /*
    * 面试题：
    * android中动画有几种？
    * 三种
    * 1 属性动画 ：真正的改变了控件的属性
    * 2 帧动画 ： 把多张图片串联起来实现连续播放的效果
    * 3 视图动画 ：普通动画虽然控件位置或者大小发生了变化，但属性并没有真正的改变，控件的监听位置并无发生改变
    *
    *
    * */
    public void startProgress(int progress){

        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, progress);

        valueAnimator.setDuration(3000);//动画执行的时间

        //监听动画
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                int value = (int) valueAnimator.getAnimatedValue();

                ProgressView.this.progress = value;

                postInvalidate();//强行绘制

            }
        });

        valueAnimator.start();
    }
}
