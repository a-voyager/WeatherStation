package com.swpuiot.ws.utils;

import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.telecom.CallScreeningService;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
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
                return R.drawable.ic_100;
            case 101:
                return R.drawable.ic_101;
            case 102:
                return R.drawable.ic_102;
            case 103:
                return R.drawable.ic_103;
            case 104:
                return R.drawable.ic_104;
            case 200:
                return R.drawable.ic_200;
            case 201:
                return R.drawable.ic_201;
            case 202:
                return R.drawable.ic_203;
            case 203:
                return R.drawable.ic_203;
            case 204:
                return R.drawable.ic_204;
            case 205:
                return R.drawable.ic_205;
            case 206:
                return R.drawable.ic_206;
            case 207:
                return R.drawable.ic_207;
            case 208:
                return R.drawable.ic_208;
            case 209:
                return R.drawable.ic_209;
            case 210:
                return R.drawable.ic_210;
            case 211:
                return R.drawable.ic_211;
            case 212:
                return R.drawable.ic_212;
            case 213:
                return R.drawable.ic_213;
            case 300:
                return R.drawable.ic_300;
            case 301:
                return R.drawable.ic_310;
            case 302:
                return R.drawable.ic_302;
            case 303:
                return R.drawable.ic_303;
            case 304:
                return R.drawable.ic_304;
            case 305:
                return R.drawable.ic_305;
            case 306:
                return R.drawable.ic_306;
            case 307:
                return R.drawable.ic_307;
            case 308:
                return R.drawable.ic_308;
            case 309:
                return R.drawable.ic_309;
            case 310:
                return R.drawable.ic_310;
            case 311:
                return R.drawable.ic_311;
            case 312:
                return R.drawable.ic_312;
            case 313:
                return R.drawable.ic_313;
            case 400:
                return R.drawable.ic_400;
            case 401:
                return R.drawable.ic_401;
            case 402:
                return R.drawable.ic_402;
            case 403:
                return R.drawable.ic_403;
            case 404:
                return R.drawable.ic_404;
            case 405:
                return R.drawable.ic_405;
            case 406:
                return R.drawable.ic_406;
            case 407:
                return R.drawable.ic_407;
            case 500:
                return R.drawable.ic_500;
            case 501:
                return R.drawable.ic_501;
            case 502:
                return R.drawable.ic_502;
            case 503:
                return R.drawable.ic_503;
            case 504:
                return R.drawable.ic_504;
            case 507:
                return R.drawable.ic_507;
            case 508:
                return R.drawable.ic_508;
            case 900:
                return R.drawable.ic_900;
            case 901:
                return R.drawable.ic_901;

            case 999:
                return R.drawable.ic_102;
            default:
                break;
        }
        Log.e(TAG, "getWeatherIcon: code error");
        return R.drawable.ic_sun;
    }

}
