package com.atguigu.p2p.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2017/6/23.
 */

public class MyScrollView extends ScrollView {

    /*
    * 注意ScrollView只能有一个子视图
    * */
    private View childView;


    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 布局加载完成时调用
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        //子视图
        if (getChildCount() > 0) {

            childView = getChildAt(0);
        }
    }

    private float lastX ;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        boolean isOnIntercept = false;
        switch (ev.getAction()) {
            case  MotionEvent.ACTION_DOWN:
                lastX = ev.getX();
                lastY = ev.getY();


                break;
            case  MotionEvent.ACTION_MOVE:
                float eventY = ev.getY();
                float eventX = ev.getX();

                float distanceX = Math.abs(eventX - lastX);
                float distanceY = Math.abs(eventY - lastY);
                if(distanceY > distanceX && distanceY > 20) {
                    //拦截banner 的事件
                    isOnIntercept = true;

                }

                lastX = ev.getX();
                lastY = ev.getY();
                break;
        }




        return isOnIntercept;
    }


      /*
    * 面试题：
    * getY()和getRawY()的区别
    * getY :指的是当前布局到父布局之间y轴的距离
    * getRawY : 指的是当前布局到屏幕之间Y轴的距离
    *
    * */

    /*
    * rect 和 rectf的区别
    * rect存int
    * rectf存float
    *
    * */
    private float lastY;
    private Rect rect;

    //用来标记动画是否执行完成
    private boolean isAnStart = false;
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (isAnStart || getChildCount()==0){

            return super.onTouchEvent(ev);
        }

        float eventY = ev.getY();//在这里，当触摸事件一直进行的时候它也会一直改变

        if (rect == null) {
            rect = new Rect();

        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                Log.e("TAG", "ACTION_DOWN");
                lastY = eventY;

                break;
            case MotionEvent.ACTION_MOVE:
                //用于记录刚开始正常情况的ScrollView的位置用于up后的回弹

                if (rect.isEmpty()) {
                    //保存一下数据，用于回弹
                    rect.set(childView.getLeft(),
                            childView.getTop(),
                            childView.getRight(),
                            childView.getBottom());
                }


                //记录在Y轴方向移动的距离
                int distanceY = (int) (eventY - lastY);

                //子视图重新定位
                childView.layout(childView.getLeft(),
                        childView.getTop() + distanceY,
                        childView.getRight(),
                        childView.getBottom() + distanceY);


                lastY = eventY;//重新赋值最后的位置

                break;
            case MotionEvent.ACTION_UP:
//                Log.e("TAG", "ACTION_UP");
                //处理回弹效果
                if (!rect.isEmpty()) {
                    float disY = childView.getTop() - rect.top;

                    /**
                     * 设置回弹的动画，缓慢回弹
                     */
                    TranslateAnimation animation = new TranslateAnimation(0,0,0,-disY);

                    animation.setDuration(300);


                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            isAnStart = true;
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            isAnStart = false;
                            childView.layout(rect.left, rect.top, rect.right, rect.bottom);

                            childView.clearAnimation();
                            rect.setEmpty();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                    childView.startAnimation(animation);
                }

                break;
        }

        return super.onTouchEvent(ev);
    }
}
