package com.swpuiot.ws;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.swpuiot.ws.base.BaseActivity;

public class TemperatureActivity extends BaseActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_temperature;
    }
}
