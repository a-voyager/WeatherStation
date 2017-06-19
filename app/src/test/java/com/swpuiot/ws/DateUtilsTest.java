package com.swpuiot.ws;

import com.swpuiot.ws.utils.DateUtils;

import org.junit.Test;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2017/06/19 21:35
 * Version: 1.0
 */

public class DateUtilsTest {
    @Test
    public void strDayOfWeek() throws Exception {

    }

    @Test
    public void dayOfWeek2Str() throws Exception {

    }

    @Test
    public void strFormatDate() throws Exception {

    }


    @Test
    public void fun() {

        String s = DateUtils.dayOfWeek2Str(DateUtils.strDayOfWeek("2017-06-24"));
        System.out.println(s);

    }
}
