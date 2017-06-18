package com.swpuiot.ws;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.swpuiot.ws.adapter.FutureRecyclerAdapter;
import com.swpuiot.ws.base.BaseActivity;
import com.swpuiot.ws.clicklistener.MyItemClickListener;
import com.swpuiot.ws.clicklistener.MyItemLongClickListener;
import com.swpuiot.ws.ds.FixedQueue;
import com.swpuiot.ws.entities.FutureDay;
import com.swpuiot.ws.ui.CropVideoView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import lecho.lib.hellocharts.model.Axis;
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
    CropVideoView mVdWeatherVideo;

    @BindView(R.id.lcv_line_chart)
    LineChartView lineChartView;
    @BindView(R.id.iv_curr_video)
    ImageView mIvCurrVideo;
    private FixedQueue<PointValue> mFixedQueue;
    private LineChartData mChartData;

    @BindView(R.id.rcv_future)
    RecyclerView futureRecyclr;

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

        // 每隔一秒钟 生成随机数
        final Random random = new Random();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        addChartValue(random.nextInt(16));
                    }
                });
            }
        };
        new Timer().schedule(task, 0, 1000);

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

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate_anim);
        mIvCurrVideo.startAnimation(animation);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        List<FutureDay> futureDays = new ArrayList<>();
        futureDays.add(new FutureDay(R.drawable.ic_daxue, "周一", "大雪 | 良", "27/19"));
        futureDays.add(new FutureDay(R.drawable.ic_cloudy, "周二", "多云 | 良", "27/19"));
        futureDays.add(new FutureDay(R.drawable.ic_dayu, "周三", "大雨 | 良", "27/19"));
        futureDays.add(new FutureDay(R.drawable.ic_sun, "周四", "晴 | 良", "27/19"));

        FutureRecyclerAdapter adapter = new FutureRecyclerAdapter(this, futureDays);
        futureRecyclr.setLayoutManager(layoutManager);
        futureRecyclr.setAdapter(adapter);
        adapter.setClickListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "点击了第" + position + "个item", Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setLongClickListener(new MyItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, "长按了第" + position + "个item", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    public void drawLine() {

        mFixedQueue = new FixedQueue<>();
        mChartData = new LineChartData();

        List<PointValue> list = mFixedQueue.list();
        Line line = new Line(list)
                .setPointRadius(2)
                .setStrokeWidth(1)
                .setCubic(true)
                .setFilled(true)
                .setAreaTransparency(15)
                .setColor(Color.GREEN);

        List<Line> lines = new ArrayList<Line>();
        lines.add(line);
        mChartData.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setAutoGenerated(false);
        mChartData.setAxisXBottom(axisX);


        Axis axisY = new Axis();  //Y轴
        axisY.setMaxLabelChars(3); //默认是3，只能看最后三个数字
        axisY.setTextSize(6);
        axisY.setHasSeparationLine(false);
        mChartData.setAxisYLeft(axisY);

        lineChartView.setInteractive(false);
        lineChartView.setLineChartData(mChartData);
    }


    public void addChartValue(float y) {
        for (PointValue value : mFixedQueue.list()) {
            value.set(value.getX() - 1, value.getY());
        }
        mFixedQueue.offer(new PointValue(mFixedQueue.getCapacity() - 1, y));
        lineChartView.setLineChartData(mChartData);


    }

}
