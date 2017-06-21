package com.swpuiot.ws.lib.src.lib.utils;


import com.swpuiot.ws.lib.src.lib.constant.Config;

/**
 * Created by wuhaojie on 17-5-25.
 */
public class Log {

    private Log() {
    }

    public static void d(String message) {
        if (Config.DEBUG)
            System.out.println(message);
    }

    public static void e(Exception e) {
        if (Config.DEBUG)
            e.printStackTrace();
    }

}
