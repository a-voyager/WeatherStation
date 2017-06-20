package com.swpuiot.ws.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.swpuiot.ws.R;
import com.swpuiot.ws.adapter.HourlyListAdapter;
import com.swpuiot.ws.base.BaseActivity;
import com.swpuiot.ws.data.HttpHelper;
import com.swpuiot.ws.entities.response.HourlyResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class VideoActivity extends BaseActivity {


    @BindView(R.id.rv_hourly)
    RecyclerView mRvHourly;
    private HourlyListAdapter mHourlyListAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HttpHelper.get().hourly("成都", this::handleHourlyResponse);

    }

    private void handleHourlyResponse(HourlyResponse hourlyResponse) {
        List<HourlyListAdapter.Item> items = new ArrayList<>();
        List<HourlyResponse.HeWeather5Bean.HourlyForecastBean> hourlyForecast = hourlyResponse.getHeWeather5().get(0).getHourly_forecast();
        for (HourlyResponse.HeWeather5Bean.HourlyForecastBean bean : hourlyForecast) {
            HourlyListAdapter.Item item = new HourlyListAdapter.Item();
            item.condCode = bean.getCond().getCode();
            item.condTxt = bean.getCond().getTxt();
            item.date = bean.getDate();
            item.hum = bean.getHum();
            item.tmp = bean.getTmp();
            item.press = bean.getPres();
            item.wind_dir = bean.getWind().getDir();
            item.wind_sc = bean.getWind().getSc();
            items.add(item);
        }
        mHourlyListAdapter.setList(items);
    }

    @Override
    protected void initViews() {
        mHourlyListAdapter = new HourlyListAdapter(this);
        mRvHourly.setAdapter(mHourlyListAdapter);
        mRvHourly.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRvHourly.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_video;
    }
}
