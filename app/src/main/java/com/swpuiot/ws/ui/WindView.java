package com.swpuiot.ws.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import top.wuhaojie.lib.utils.DensityUtil;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2017/06/18 14:25
 * Version: 1.0
 */
public class WindView extends View {
    private static final int DEFAULT_COLOR = Color.GRAY;
    private static final int DEFAULT_WIDTH = 1;//画笔宽度1dp
    private static final float LENGTH_1 = 5;//下面三角形高度5dp
    private static final float ALPHA = (float) (Math.PI / 6);//旋转角度
    private static final int DELAY = 30;

    private Paint mPaint;
    private Path mPath;
    private float mAngle = 0;//旋转角度 通过改变角度实现旋转动画
    private float length;
    private int length1;

    public WindView(Context context) {
        this(context, null);
    }

    public WindView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WindView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //初始化
    private void init() {
        mPaint = new Paint();
        mPath = new Path();
        mPaint.setColor(DEFAULT_COLOR);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mAngle = (float) (mAngle + 3 * Math.PI / 360);

        float centerX = getWidth() / 2;
        float centerY = getHeight() * 4 / 9.0f;
        mPaint.setStrokeWidth(DensityUtil.dip2px(getContext(), DEFAULT_WIDTH));
        //绘制风车的身体
        canvas.drawLine(centerX, centerY, centerX - getWidth() / 10, getHeight(), mPaint);
        canvas.drawLine(centerX, centerY, centerX + getWidth() / 10, getHeight(), mPaint);

        //绘制叶片 叶片由两个三角形组成 length1是下面三角形的高 length为整个叶片长度
        length = (float) (DensityUtil.dip2px(getContext(), LENGTH_1) * Math.sin(ALPHA)
                + getHeight() * 2 / 9.0f);
        length1 = DensityUtil.dip2px(getContext(), LENGTH_1);

        float alpha = (float) (Math.PI / 2 - ALPHA + mAngle);
        drawWindMill(canvas, centerX, centerY, alpha);

        canvas.save();
        canvas.rotate(120, centerX, centerY);
        drawWindMill(canvas, centerX, centerY, alpha);
        canvas.restore();

        canvas.save();
        canvas.rotate(240, centerX, centerY);
        drawWindMill(canvas, centerX, centerY, alpha);
        canvas.restore();

        mPath.reset();
        postInvalidateDelayed(DELAY);
    }

    private void drawWindMill(Canvas canvas, float centerX, float centerY, float alpha) {
        mPath.moveTo(centerX, centerY);
        mPath.lineTo((float) (centerX + length1 * Math.cos(alpha)),
                (float) (centerY - length1 * Math.sin(alpha)));

        mPath.lineTo((float) (centerX + length * Math.cos(Math.PI / 2 + mAngle)),
                (float) (centerY - length * Math.sin(Math.PI / 2 + mAngle)));

        mPath.lineTo((float) (centerX + length1 * Math.cos(alpha + 2 * ALPHA)),
                (float) (centerY - length1 * Math.sin(alpha + 2 * ALPHA)));
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }
}