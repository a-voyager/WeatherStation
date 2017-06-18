package com.swpuiot.ws;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.swpuiot.ws.base.BaseActivity;

import butterknife.BindView;


public class MainActivity extends BaseActivity  {
    @BindView(R.id.tt_window_temp)
    TextView showTemp;

    @BindView(R.id.tt_window_weather_stat)
    TextView weartherStat;
    @BindView(R.id.tt_window_address)
    TextView userAdd;

    @BindView(R.id.tt_window_air_quality)
    TextView airQuality;
    @BindView(R.id.tt_window_air_temp)
    TextView airTemp;
    @BindView(R.id.tt_window_air_hump)
    TextView airHump;

    @BindView(R.id.ll_window_air_quality)
    LinearLayout qualityLayout;
    @BindView(R.id.ll_window_air_temp)
    LinearLayout tempLayout;
    @BindView(R.id.ll_window_air_hump)
    LinearLayout humpLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

}
