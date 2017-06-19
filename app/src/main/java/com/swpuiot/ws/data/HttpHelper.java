package com.swpuiot.ws.data;

import android.util.Log;

import com.swpuiot.ws.entities.response.ForecastResponse;
import com.swpuiot.ws.entities.response.HourlyResponse;
import com.swpuiot.ws.entities.response.SuggestResponse;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import top.wuhaojie.lib.http.RetrofitHttpHelper;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2017/06/18 12:18
 * Version: 1.0
 */

public class HttpHelper {

    public static final String TAG = "HttpHelper";
    private volatile static HttpHelper mHttpHelper;

    private final CommonApi mCommonApi;
    private final WeatherNetApi mWeatherNetApi;

    private HttpHelper() {
        mCommonApi = new RetrofitHttpHelper<>(CommonApi.BASE_URL, CommonApi.class).getService();
        mWeatherNetApi = new RetrofitHttpHelper<>(WeatherNetApi.BASE_URL, WeatherNetApi.class).getService();
    }

    public static HttpHelper get() {
        if (mHttpHelper == null) {
            synchronized (HttpHelper.class) {
                if (mHttpHelper == null) {
                    mHttpHelper = new HttpHelper();
                }
            }
        }
        return mHttpHelper;
    }


    public void forecast(String city, String language, final Action1<? super ForecastResponse> onNext) {
        mWeatherNetApi
                .forecast(city, WeatherNetApi.KEY, language)
                .compose(new TransThread<>())
                .subscribe(onNext);
    }

    public void suggestion(String city, String language, final Action1<? super SuggestResponse> onNext) {
        mWeatherNetApi
                .suggestion(city, WeatherNetApi.KEY, language)
                .compose(new TransThread<>())
                .subscribe(onNext);
    }

    public void hourly(String city, final Action1<? super HourlyResponse> onNext) {
        mWeatherNetApi
                .hourly(city, WeatherNetApi.KEY)
                .compose(new TransThread<>())
                .subscribe(onNext);
    }


    private class TransThread<H> implements Observable.Transformer<H, H> {

        @Override
        public Observable<H> call(Observable<H> hObservable) {
            return hObservable.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .doOnError(throwable -> Log.e(TAG, "error: " + throwable))
                    .unsubscribeOn(Schedulers.io());
        }
    }

}
