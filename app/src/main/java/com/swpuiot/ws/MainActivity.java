package com.swpuiot.ws;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.swpuiot.ws.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;


public class MainActivity extends BaseActivity {


    @BindView(R.id.tt_window_temp)
    TextView mTtWindowTemp;
    @BindView(R.id.tt_window_weather_stat)
    TextView mTtWindowWeatherStat;
    @BindView(R.id.tt_window_address)
    TextView mTtWindowAddress;
    @BindView(R.id.tt_window_air_quality)
    TextView mTtWindowAirQuality;
    @BindView(R.id.tt_window_air_stat)
    TextView mTtWindowAirStat;
    @BindView(R.id.ll_window_air_quality)
    LinearLayout mLlWindowAirQuality;
    @BindView(R.id.tt_window_air_temp)
    TextView mTtWindowAirTemp;
    @BindView(R.id.ll_window_air_temp)
    LinearLayout mLlWindowAirTemp;
    @BindView(R.id.tt_window_air_hump)
    TextView mTtWindowAirHump;
    @BindView(R.id.ll_window_air_hump)
    LinearLayout mLlWindowAirHump;
    @BindView(R.id.tv_wind_speed)
    TextView mTvWindSpeed;
    @BindView(R.id.tv_wind_direction)
    TextView mTvWindDirection;
    @BindView(R.id.tv_wind_desc)
    TextView mTvWindDesc;
    @BindView(R.id.vd_weather_video)
    VideoView mVdWeatherVideo;

    @BindView(R.id.lcv_line_chart)
    LineChartView lineChartView;

    private LineChartData data;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mVdWeatherVideo.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video_weather));
        mVdWeatherVideo.requestFocus();
        mVdWeatherVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mVdWeatherVideo.seekTo(0);
                mVdWeatherVideo.start();
            }
        });
        mVdWeatherVideo.start();


        drawLine();
    }


    @Override
    protected void onStart() {
        super.onStart();
        mVdWeatherVideo.resume();
    }


    @Override
    protected void onStop() {
        super.onStop();
        mVdWeatherVideo.pause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVdWeatherVideo.stopPlayback();
    }

    @Override
    protected void initViews() {


    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    public void drawLine() {
        List<PointValue> values = new ArrayList<PointValue>();
        List<AxisValue> mAxisValues = new ArrayList<AxisValue>();
        data = new LineChartData();
        for (int i = 0; i < 10; i++) {
            values.add(new PointValue(i, new Random().nextInt(5)));
            mAxisValues.add(new AxisValue(i).setLabel(i + "")); //为每个对应的i设置相应的label(显示在X轴)
        }
        Line line = new Line(values).setColor(Color.BLUE).setCubic(true);
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);
        data.setLines(lines);
        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(true);
        axisX.setTextColor(Color.BLUE);
        axisX.setMaxLabelChars(1);
        axisX.setValues(mAxisValues);
        data.setAxisXBottom(axisX);

        Axis axisY = new Axis();  //Y轴
        axisY.setName("幅度");
        axisY.setMaxLabelChars(7); //默认是3，只能看最后三个数字
        data.setAxisYLeft(axisY);

        lineChartView.setInteractive(true);
        lineChartView.setZoomType(ZoomType.HORIZONTAL);
        lineChartView.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChartView.setLineChartData(data);
    }

}
