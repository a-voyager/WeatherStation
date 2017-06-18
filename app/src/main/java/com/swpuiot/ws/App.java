package com.swpuiot.ws;

import android.app.Application;

import top.wuhaojie.lib.image.ImageLoader;
import top.wuhaojie.lib.image.impl.GlideImageLoader;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2017/06/18 12:23
 * Version: 1.0
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoader.init(GlideImageLoader.create(this));    // 初始化 ImageLoader
    }
}
