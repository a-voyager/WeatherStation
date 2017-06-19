package com.swpuiot.ws.utils;

import android.text.TextUtils;

import top.wuhaojie.lib.utils.Preconditions;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2017/06/19 20:44
 * Version: 1.0
 */

public class DateUtils {

    private DateUtils() {
    }

    public int strFormatDate(String date) {
        Preconditions.checkArgument(!TextUtils.isEmpty(date));
//        Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).parse("2017-06-19");
        return 0;
    }

}
