package com.swpuiot.ws.utils;

import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;

import com.swpuiot.ws.activities.HumpActivity;
import com.swpuiot.ws.activities.LightActivity;
import com.swpuiot.ws.activities.SettingsActivity;
import com.swpuiot.ws.activities.TemperatureActivity;
import com.swpuiot.ws.activities.VideoActivity;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2017/06/19 13:52
 * Version: 1.0
 */

public class IntentManager {

    private IntentManager() {
    }


    public static Intent toTemperatureActivity(Context context) {
        return new Intent(context, TemperatureActivity.class);
    }


    public static Intent toVideoActivity(Context context) {
        return new Intent(context, VideoActivity.class);
    }


    public static Intent toSettingActivity(Context context) {
        return new Intent(context, SettingsActivity.class);
    }

    public static Intent toLightActivity(Context context) {
        return new Intent(context, LightActivity.class);
    }

    public static Intent toHumpActivity(Context context){
        return new Intent(context, HumpActivity.class);
    }
}
