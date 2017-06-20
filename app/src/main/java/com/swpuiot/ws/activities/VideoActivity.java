package com.swpuiot.ws.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import static io.vov.vitamio.MediaPlayer.MEDIA_INFO_BUFFERING_END;
import static io.vov.vitamio.MediaPlayer.MEDIA_INFO_BUFFERING_START;
import static io.vov.vitamio.widget.VideoView.VIDEO_LAYOUT_SCALE;

public class VideoActivity extends BaseActivity {


    private static final String TAG = "VideoActivity";
    @BindView(R.id.rv_hourly)
    RecyclerView mRvHourly;
    @BindView(R.id.vd_video)
    VideoView mVdVideo;
    @BindView(R.id.tv_buffer_percent)
    TextView mTvBufferPercent;
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
//        mVdVideo.setVideoPath("http://cdn.moji.com/websrc/video/video314.mp4");
        mVdVideo.setVideoPath(Constants.MEDIA_SERVER_URL);
        mVdVideo.setOnErrorListener((mp, what, extra) -> {
            Log.d(TAG, "onError: " + what);
            return true;
        });
        mVdVideo.setOnBufferingUpdateListener((mp, percent) -> {
            mTvBufferPercent.setText("缓冲中..." + percent + "%");
        });
        mVdVideo.setOnInfoListener((mp, what, extra) -> {
            switch (what) {
                case MEDIA_INFO_BUFFERING_START:
                    Log.d(TAG, "onInfo: BUFFERING_START");
                    mTvBufferPercent.setVisibility(View.VISIBLE);
                    break;
                case MEDIA_INFO_BUFFERING_END:
                    Log.d(TAG, "onInfo: BUFFERING_END");
                    mTvBufferPercent.setVisibility(View.INVISIBLE);
                    break;
            }
            return true;
        });
        mVdVideo.setHardwareDecoder(true);
        mVdVideo.setVideoLayout(VIDEO_LAYOUT_SCALE, (float) (16.0 / 9));
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_video;
    }
}
