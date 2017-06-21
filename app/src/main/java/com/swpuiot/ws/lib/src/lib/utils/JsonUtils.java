package com.swpuiot.ws.lib.src.lib.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.swpuiot.ws.lib.src.lib.exception.JsonConvertException;


/**
 * Json helper class
 * Created by wuhaojie on 17-5-25.
 */
public class JsonUtils {

    private static Gson mGson = new Gson();

    private JsonUtils() {
    }

    public static String toJson(Object src) {
        return mGson.toJson(src);
    }

    /**
     * @param json     json str
     * @param classOfT class
     * @return if convert error, return null; else return Object
     * @throws JsonConvertException throw when convert error
     */
    public static <T> T fromJson(String json, Class<T> classOfT) throws JsonConvertException {
        T result = null;
        try {
            result = mGson.fromJson(json, classOfT);
        } catch (JsonSyntaxException e) {
            throw new JsonConvertException();
        }
        return result;
    }

}
