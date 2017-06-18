package com.swpuiot.ws;

import android.support.test.runner.AndroidJUnit4;

import com.swpuiot.ws.data.HttpHelper;
import com.swpuiot.ws.entities.response.ForecastResponse;

import org.junit.Test;
import org.junit.runner.RunWith;

import rx.functions.Action1;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2017/06/19 02:27
 * Version: 1.0
 */
@RunWith(AndroidJUnit4.class)
public class HttpHelperTest {
    @Test
    public void get() throws Exception {

    }

    @Test
    public void forecast() throws Exception {
        HttpHelper.get().forecast("成都", "zh", new Action1<ForecastResponse>() {
            @Override
            public void call(ForecastResponse forecastResponse) {
                System.out.println(forecastResponse);
            }
        });
        while (true) ;
    }


}
