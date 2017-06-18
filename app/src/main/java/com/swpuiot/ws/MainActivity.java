package com.swpuiot.ws;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.swpuiot.ws.base.BaseActivity;

import butterknife.BindView;


public class MainActivity extends BaseActivity {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

}
