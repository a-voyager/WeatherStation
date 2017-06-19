package com.swpuiot.ws.utils;

import android.support.annotation.IntRange;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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

    @IntRange(from = 1, to = 7)
    public static int strDayOfWeek(String s) {
        Preconditions.checkArgument(!"".equals(s));
        try {
            Date date = Singleton.dateFormat.parse(s);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.get(Calendar.DAY_OF_WEEK);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 1;
    }


    public static String dayOfWeek2Str(@IntRange(from = 1, to = 7) int dayOfWeek) {
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                return "周日";
            case Calendar.MONDAY:
                return "周一";
            case Calendar.TUESDAY:
                return "周二";
            case Calendar.WEDNESDAY:
                return "周三";
            case Calendar.THURSDAY:
                return "周四";
            case Calendar.FRIDAY:
                return "周五";
            case Calendar.SATURDAY:
                return "周六";
            default:
        }
        return "";
    }

    private static class Singleton {
        static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
    }

}
