package com.yl.propertyanimationdemo.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

/**
 * 雷达图
 * Created by yangle on 2017/1/5.
 */
public class RadarView extends View {

    // 数据个数
    private int dataCount = 6;
    // 每个角的弧度
    private float angle = (float) (Math.PI * 2 / dataCount);
    // 雷达图半径
    private float radius;
    // 中心X坐标
    private int centerX;
    // 中心Y坐标
    private int centerY;
    // 设备状态：1.在线，2.报警
    private int[] deviceStatus = {ONLINE, ONLINE, ONLINE, ONLINE, ONLINE, ONLINE};
    // 在线
    public static final int ONLINE = 1;
    // 报警
    public static final int ALARM = 2;

    // 雷达区画笔
    private Paint mainPaint;
    // 圆点画笔
    private Paint pointPaint;
    // 报警圆点画笔
    private Paint alarmPointPaint;
    // 数据区画笔
    private Paint valuePaint;

    // 是否正在播放动画
    private boolean isAnimationPlaying = false;

    public RadarView(Context context) {
        this(context, null);
    }

    public RadarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mainPaint = new Paint();
        mainPaint.setAntiAlias(true);
        mainPaint.setColor(Color.WHITE);
        mainPaint.setStyle(Paint.Style.STROKE);

        pointPaint = new Paint();
        pointPaint.setAntiAlias(true);
        pointPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        alarmPointPaint = new Paint();
        alarmPointPaint.setAntiAlias(true);
        alarmPointPaint.setColor(Color.parseColor("#FEF612"));
        alarmPointPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        valuePaint = new Paint();
        valuePaint.setAntiAlias(true);
        valuePaint.setColor(Color.WHITE);
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // 雷达图半径
        radius = Math.max(h, w) / 2 * 0.8f;
        // 中心坐标
        centerX = w / 2;
        centerY = h / 2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制多边形
        drawPolygon(canvas);
        // 绘制点
        drawPoints(canvas);
        // 绘制连接线
        drawLines(canvas);
        // 绘制覆盖区域
        drawRegion(canvas);
    }

    /**
     * 绘制多边形
     *
     * @param canvas 画布
     */
    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < dataCount; i++) {
            if (i == 0) {
                path.moveTo(getPoint(i).x, getPoint(i).y);
            } else {
                path.lineTo(getPoint(i).x, getPoint(i).y);
            }
        }
        //闭合路径
        path.close();

        mainPaint.setAlpha(150);
        mainPaint.setStrokeWidth(1f);
        canvas.drawPath(path, mainPaint);
    }

    /**
     * 绘制点
     *
     * @param canvas 画布
     */
    private void drawPoints(Canvas canvas) {
        for (int i = 0; i < dataCount; i++) {
            if (deviceStatus[i] == ONLINE) {
                pointPaint.setColor(Color.WHITE);
                pointPaint.setAlpha(255);
                canvas.drawCircle(getPoint(i).x, getPoint(i).y, dp2px(3), pointPaint);

            } else if (deviceStatus[i] == ALARM) {
                pointPaint.setColor(Color.WHITE);
                pointPaint.setAlpha(255);
                canvas.drawCircle(getPoint(i).x, getPoint(i).y, dp2px(3), pointPaint);

                if (!isAnimationPlaying) {
                    isAnimationPlaying = true;
                    ValueAnimator animator = ValueAnimator.ofInt(0, 100);
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            int currentValue = (int) animation.getAnimatedValue();
                            // 给报警点设置0至100的透明度
                            alarmPointPaint.setAlpha(currentValue);
                            Log.i("啦啦啦啦", "currentValue：" + currentValue);
                            invalidate();
                        }
                    });
                    // 单次动画时长1.5s
                    animator.setDuration(1500);
                    // 无限循环播放动画
                    animator.setRepeatCount(ValueAnimator.INFINITE);
                    // 循环时倒序播放
                    animator.setRepeatMode(ValueAnimator.REVERSE);
                    animator.start();
                } else {
                    canvas.drawCircle(getPoint(i).x, getPoint(i).y, dp2px(8), alarmPointPaint);
                }
            }
        }
    }

    /**
     * 绘制连接线
     *
     * @param canvas 画布
     */
    private void drawLines(Canvas canvas) {
        Path path = new Path();
        mainPaint.setAlpha(100);
        mainPaint.setStrokeWidth(0.3f);

        for (int i = 0; i < dataCount; i++) {
            path.reset();
            path.moveTo(centerX, centerY);
            path.lineTo(getPoint(i).x, getPoint(i).y);
            canvas.drawPath(path, mainPaint);
        }
    }

    /**
     * 绘制覆盖区域
     *
     * @param canvas 画布
     */
    private void drawRegion(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < dataCount; i++) {
            if (i == 0) {
                path.moveTo(getPoint(i, 0.8f).x, getPoint(i, 0.8f).y);
            } else {
                path.lineTo(getPoint(i, 0.8f).x, getPoint(i, 0.8f).y);
            }
        }

        // 绘制填充区域
        valuePaint.setAlpha(50);
        canvas.drawPath(path, valuePaint);

        path.reset();
        for (int i = 0; i < dataCount; i++) {
            if (i == 0) {
                path.moveTo(getPoint(i, 0.6f).x, getPoint(i, 0.6f).y);
            } else {
                path.lineTo(getPoint(i, 0.6f).x, getPoint(i, 0.6f).y);
            }
        }

        // 绘制填充区域
        valuePaint.setAlpha(100);
        canvas.drawPath(path, valuePaint);
    }

    /**
     * 获取多边形各个点的坐标
     *
     * @param position 位置
     * @return 点坐标
     */
    private Point getPoint(int position) {
        return getPoint(position, 1);
    }

    /**
     * 获取多边形各个点的坐标
     *
     * @param position 位置
     * @param percent  百分比
     * @return 点坐标
     */
    private Point getPoint(int position, float percent) {
        if (position == 0) {
            return new Point(centerX + radius * percent, centerY);
        } else {
            float x = (float) (centerX + radius * Math.cos(angle * position) * percent);
            float y = (float) (centerY + radius * Math.sin(angle * position) * percent);
            return new Point(x, y);
        }
    }

    private int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    private int sp2px(float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                getResources().getDisplayMetrics());
    }

    /**
     * 用于存储点坐标
     */
    private class Point {
        // X轴坐标
        private float x;
        // Y轴坐标
        private float y;

        private Point(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    public void setDeviceStatus(int[] deviceStatus) {
        this.deviceStatus = deviceStatus;
        invalidate();
    }
}
