package com.swpuiot.ws.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.view.menu.MenuWrapperFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.swpuiot.ws.R;
import com.swpuiot.ws.adapter.AdviseAdapter;
import com.swpuiot.ws.base.BaseActivity;
import com.swpuiot.ws.data.HttpHelper;
import com.swpuiot.ws.entities.Advise;
import com.swpuiot.ws.entities.response.ForecastResponse;
import com.swpuiot.ws.entities.response.SuggestResponse;
import com.swpuiot.ws.utils.CodeTransformer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleBiFunction;

import butterknife.BindView;
import rx.internal.schedulers.EventLoopsScheduler;
import top.wuhaojie.lib.image.ImageLoader;

public class TemperatureActivity extends BaseActivity {
    @BindView(R.id.rcl_temp_advise)
    RecyclerView adviseList;
    @BindView(R.id.img_temp_head)
    ImageView tomLogo;
    @BindView(R.id.tv_temp_head_stat)
    TextView tomStat;
    @BindView(R.id.tv_temp_head_wind)
    TextView tomWindName;
    @BindView(R.id.tv_temp_head_wind_grade)
    TextView tomWindGrade;
    @BindView(R.id.tv_temp_head_wind_space)
    TextView tomWindSpace;
    @BindView(R.id.tv_temp_head_temp)
    TextView tomTemp;
    @BindView(R.id.tv_circular_today_address)
    TextView cirAddress;
    @BindView(R.id.tv_circular_today_temp)
    TextView cirTemp;
    @BindView(R.id.tv_circular_today_air)
    TextView cirStat;


    private static final String TAG = "TemperatureActivity";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HttpHelper.get().suggestion("成都", "zh", this::handleSuggestResponse);
        HttpHelper.get().forecast("成都", "zh", this::handleForecastResponse);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initViews() {
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_temperature;
    }


    private void handleSuggestResponse(SuggestResponse suggestResponse) {
        if (suggestResponse == null) {
            Log.d(TAG, "suggestResponse is a null object");
            return;
        }
        List<Advise> advises = new ArrayList<>();
        advises.add(new Advise(R.drawable.ic_300, "阳光浴—" + suggestResponse.getHeWeather5().get(0).getSuggestion().getComf().getBrf(),
                suggestResponse.getHeWeather5().get(0).getSuggestion().getComf().getTxt()));
        advises.add(new Advise(R.drawable.ic_multi_cities, "洗车—" + suggestResponse.getHeWeather5().get(0).getSuggestion().getCw().getBrf(),
                suggestResponse.getHeWeather5().get(0).getSuggestion().getCw().getTxt()));
        advises.add(new Advise(R.drawable.icon_cloth, "穿衣—" + suggestResponse.getHeWeather5().get(0).getSuggestion().getDrsg().getBrf(),
                suggestResponse.getHeWeather5().get(0).getSuggestion().getDrsg().getTxt()));
        advises.add(new Advise(R.drawable.icon_flu, "感冒—" + suggestResponse.getHeWeather5().get(0).getSuggestion().getFlu().getBrf(),
                suggestResponse.getHeWeather5().get(0).getSuggestion().getFlu().getTxt()));
        advises.add(new Advise(R.drawable.icon_sport, "运动—" + suggestResponse.getHeWeather5().get(0).getSuggestion().getSport().getBrf(),
                suggestResponse.getHeWeather5().get(0).getSuggestion().getSport().getTxt()));
        advises.add(new Advise(R.drawable.icon_travel, "出游—" + suggestResponse.getHeWeather5().get(0).getSuggestion().getTrav().getBrf(),
                suggestResponse.getHeWeather5().get(0).getSuggestion().getTrav().getTxt()));
        advises.add(new Advise(R.drawable.ic_100, "紫外线—" + suggestResponse.getHeWeather5().get(0).getSuggestion().getUv().getBrf(),
                suggestResponse.getHeWeather5().get(0).getSuggestion().getUv().getTxt()));
        AdviseAdapter adpter = new AdviseAdapter(this, advises);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adviseList.setLayoutManager(layoutManager);
        adviseList.setAdapter(adpter);
    }

    private void handleForecastResponse(ForecastResponse forecastResponse) {
        if (forecastResponse == null) return;
        tomLogo.setImageResource(CodeTransformer.getWeatherIcon(Integer.parseInt(forecastResponse.getHeWeather5().get(0).getDaily_forecast().get(1).getCond().getCode_d())));
        tomStat.setText(forecastResponse.getHeWeather5().get(0).getDaily_forecast().get(1).getCond().getTxt_d());
        tomWindName.setText(forecastResponse.getHeWeather5().get(0).getDaily_forecast().get(1).getWind().getDir());
        tomWindGrade.setText(forecastResponse.getHeWeather5().get(0).getDaily_forecast().get(1).getWind().getSc());
        tomWindSpace.setText(forecastResponse.getHeWeather5().get(0).getDaily_forecast().get(1).getWind().getSpd() + "km/h");
        tomTemp.setText(forecastResponse.getHeWeather5().get(0).getDaily_forecast().get(1).getTmp().getAx() + "℃/"
                + forecastResponse.getHeWeather5().get(0).getDaily_forecast().get(1).getTmp().getIn() + "℃");

        cirAddress.setText(forecastResponse.getHeWeather5().get(0).getBasic().getCity());
        cirTemp.setText(forecastResponse.getHeWeather5().get(0).getDaily_forecast().get(0).getTmp().getAx() + "/"
                + forecastResponse.getHeWeather5().get(0).getDaily_forecast().get(0).getTmp().getIn());



    }

}
