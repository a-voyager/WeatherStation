package com.swpuiot.ws.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.swpuiot.ws.R;
import com.swpuiot.ws.base.BaseActivity;
import com.swpuiot.ws.data.HttpHelper;
import com.swpuiot.ws.entities.response.SuggestResponse;

public class TemperatureActivity extends BaseActivity {

    private static final String TAG = "TemperatureActivity";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HttpHelper.get().suggestion("成都", "zh", this::handleSuggestResponse);
    }


    @Override
    protected void initViews() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_temperature;
    }


    private void handleSuggestResponse(SuggestResponse suggestResponse) {
        Log.d(TAG, "call: " + suggestResponse);
    }

}
