package com.swpuiot.ws.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2017/06/18 23:17
 * Version: 1.0
 */

public class CropVideoView extends VideoView {

    public CropVideoView(Context context) {
        super(context);
    }

    public CropVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CropVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int leftAdjustment;
    private int topAdjustment;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int videoWidth = getMeasuredWidth();
        int videoHeight = getMeasuredHeight();

        int viewWidth = getDefaultSize(0, widthMeasureSpec);
        int viewHeight = getDefaultSize(0, heightMeasureSpec);

        leftAdjustment = 0;
        topAdjustment = 0;
        if (videoWidth == viewWidth) {
            int newWidth = (int) ((float) videoWidth / videoHeight * viewHeight);
            setMeasuredDimension(newWidth, viewHeight);
            leftAdjustment = -(newWidth - viewWidth) / 2;
        } else {
            int newHeight = (int) ((float) videoHeight / videoWidth * viewWidth);
            setMeasuredDimension(viewWidth, newHeight);
            topAdjustment = -(newHeight - viewHeight) / 2;

        }
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l + leftAdjustment, t + topAdjustment, r + leftAdjustment, b + topAdjustment);
    }
}
