package com.swpuiot.ws.data;

import com.swpuiot.ws.entities.response.ForecastResponse;
import com.swpuiot.ws.entities.response.SuggestResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2017/06/19 01:58
 * Version: 1.0
 */

public interface WeatherNetApi {

    String BASE_URL = "https://free-api.heweather.com/v5/";
    String KEY = "9ec533308d184fce963275fe0dae2378";


    @GET("forecast")
    Observable<ForecastResponse> forecast(@Query("city") String city, @Query("key") String key, @Query("lang") String language);


    @GET("suggestion")
    Observable<SuggestResponse> suggestion(@Query("city") String city, @Query("key") String key, @Query("lang") String language);


}
