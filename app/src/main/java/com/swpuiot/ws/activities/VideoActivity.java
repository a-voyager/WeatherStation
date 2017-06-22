package com.swpuiot.ws.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.swpuiot.ws.R;
import com.swpuiot.ws.adapter.HourlyListAdapter;
import com.swpuiot.ws.base.BaseActivity;
import com.swpuiot.ws.constant.Constants;
import com.swpuiot.ws.data.HttpHelper;
import com.swpuiot.ws.entities.response.HourlyResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.widget.VideoView;
import top.wuhaojie.lib.utils.PreferenceUtils;

import static io.vov.vitamio.widget.VideoView.VIDEO_LAYOUT_SCALE;

public class VideoActivity extends BaseActivity {


    private static final String TAG = "VideoActivity";
    @BindView(R.id.rv_hourly)
    RecyclerView mRvHourly;
    @BindView(R.id.vd_video)
    VideoView mVdVideo;
    @BindView(R.id.tv_wait)
    TextView mTvWaitCenter;
    @BindView(R.id.tv_buffer_percent)
    TextView mTvPercentCenter;
    private HourlyListAdapter mHourlyListAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HttpHelper.get().hourly("成都", this::handleHourlyResponse);


        initVideo();
    }


    private void initVideo() {
        if (!LibsChecker.checkVitamioLibs(this))
            return;
        String url = PreferenceUtils.getInstance(this).getStringParam(Constants.CONFIG_KEY.MEDIA_SERVER, Constants.MEDIA_SERVER_URL) + "0";
        mVdVideo.setVideoPath(url);
        mTvWaitCenter.setVisibility(View.VISIBLE);
        mTvWaitCenter.setText("请稍后...");
        mVdVideo.setOnPreparedListener(mp -> mTvWaitCenter.setVisibility(View.INVISIBLE));
        mVdVideo.setOnErrorListener((mp, what, extra) -> {
            Log.d(TAG, "onError: " + what);
            mTvWaitCenter.setText("播放错误: " + what + ", " + extra);
            return true;
        });
        mVdVideo.setOnBufferingUpdateListener((mp, percent) -> mTvPercentCenter.setText("" + percent + ""));
        mVdVideo.setHardwareDecoder(true);
        mVdVideo.setVideoLayout(VIDEO_LAYOUT_SCALE, (float) (4.0 / 3));
        mVdVideo.start();
    }


    private void play(int i) {
        String url = PreferenceUtils.getInstance(this).getStringParam(Constants.CONFIG_KEY.MEDIA_SERVER, Constants.MEDIA_SERVER_URL) + i;
        mVdVideo.setVideoPath(url);
        mVdVideo.start();
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

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mHourlyListAdapter = new HourlyListAdapter(this);
        mRvHourly.setAdapter(mHourlyListAdapter);
        mRvHourly.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRvHourly.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_video, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_video_1:
                play(1);
                break;
            case R.id.action_video_2:
                play(2);
                break;
            case R.id.action_video_3:
                play(3);
                break;
            case R.id.action_video_4:
                play(4);
                break;
        }

        return true;
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_video;
    }
}
