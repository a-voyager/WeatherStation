package com.swpuiot.ws;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.swpuiot.ws.base.BaseActivity;


public class MainActivity extends BaseActivity {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

}
