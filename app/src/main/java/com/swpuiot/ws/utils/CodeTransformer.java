package com.swpuiot.ws.utils;

import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.util.Log;

import com.swpuiot.ws.R;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2017/06/19 16:15
 * Version: 1.0
 */

public class CodeTransformer {

    private static final String TAG = "CodeTransformer";

    private CodeTransformer() {
    }


    @DrawableRes
    public static int getWeatherIcon(@IntRange(from = 100, to = 999) int code) {
        switch (code) {
            case 100:
                return R.drawable.ic_sun;
            // TODO: 17-6-19 添加所有其它情况 参见 https://www.heweather.com/documents/condition
            default:
        }
        Log.e(TAG, "getWeatherIcon: code error");
        return R.drawable.ic_sun;
    }

}
