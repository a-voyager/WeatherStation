package com.swpuiot.ws.data;

import com.swpuiot.ws.entities.response.FullInfoResponse;
import com.swpuiot.ws.entities.response.TomorrowForestResponse;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2017/06/18 12:15
 * Version: 1.0
 */

interface CommonApi {

    String BASE_URL = "http://192.168.0.110:8080/";


    @GET("Inf/getInf")
    Observable<FullInfoResponse> fullInfo();

    @GET("future/getFuture")
    Observable<TomorrowForestResponse> tomorrow();

}
