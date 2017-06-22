package com.swpuiot.ws.activities;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.swpuiot.ws.R;
import com.swpuiot.ws.adapter.FutureRecyclerAdapter;
import com.swpuiot.ws.base.BaseActivity;
import com.swpuiot.ws.constant.Constants;
import com.swpuiot.ws.data.HttpHelper;
import com.swpuiot.ws.ds.FixedQueue;
import com.swpuiot.ws.entities.FutureDay;
import com.swpuiot.ws.entities.Message;
import com.swpuiot.ws.entities.SensorData;
import com.swpuiot.ws.entities.response.ForecastResponse;
import com.swpuiot.ws.entities.response.FullInfoResponse;
import com.swpuiot.ws.entities.response.TomorrowForestResponse;
import com.swpuiot.ws.lib.src.lib.Connector;
import com.swpuiot.ws.lib.src.lib.callback.MessageCallBack;
import com.swpuiot.ws.ui.CropVideoView;
import com.swpuiot.ws.utils.CodeTransformer;
import com.swpuiot.ws.utils.DateUtils;
import com.swpuiot.ws.utils.IntentManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import top.wuhaojie.lib.utils.PreferenceUtils;


public class MainActivity extends BaseActivity {


    public static final String TAG = "MainActivity";

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
    @BindView(R.id.ll_window_light)
    LinearLayout mLlWindowAirQuality;
    @BindView(R.id.tt_window_air_temp)
    TextView mTtWindowAirTemp;
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
    @BindView(R.id.ll_window_air_temp)
    LinearLayout mLlWindowAirTemp;
    @BindView(R.id.c_toolbar)
    CollapsingToolbarLayout mCToolbar;
    @BindView(R.id.iv_weather_icon)
    ImageView mIvWeatherIcon;
    @BindView(R.id.tv_weather_text)
    TextView mTvWeatherText;
    @BindView(R.id.tv_weather_tmp)
    TextView mTvWeatherTmp;
    @BindView(R.id.tv_weather_max_min)
    TextView mTvWeatherMaxMin;
    @BindView(R.id.tv_weather_wind)
    TextView mTvWeatherWind;

    private FixedQueue<PointValue> mFixedQueue;
    private LineChartData mChartData;

    @BindView(R.id.rcv_future)
    RecyclerView futureRecyclr;


    @OnClick(R.id.ll_window_air_temp)
    void onTemperatureClick() {
        startActivity(IntentManager.toTemperatureActivity(this));
    }

    @OnClick(R.id.card_video)
    void onVideoCardClick() {
        startActivity(IntentManager.toVideoActivity(this));
    }

    @OnClick(R.id.ll_window_light)
    void onLightLayoutClick() {
        startActivity(IntentManager.toLightActivity(this));
    }

    @OnClick(R.id.ll_window_air_hump)
    void onHumpLayoutClick() {
        startActivity(IntentManager.toHumpActivity(this));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mVdWeatherVideo.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video_weather));
        mVdWeatherVideo.setOnCompletionListener(mp -> {
            mVdWeatherVideo.seekTo(0);
            mVdWeatherVideo.start();
        });

        drawLine();


        HttpHelper.get().forecast("成都", "zh", this::handleForecastResponse);

        String mqttUrl = PreferenceUtils.getInstance(this).getStringParam(Constants.CONFIG_KEY.MESSAGE_SERVER, "tcp://114.215.144.204:61613");


        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        @SuppressLint("HardwareIds") String deviceId = telephonyManager.getDeviceId();
        Log.d(TAG, "onCreate: " + deviceId);


        Observable.fromCallable(() -> {
            Connector<Message> connector = new Connector.Builder<Message>()
                    .setClientId(deviceId)
                    .setClientTopic("app")
                    .setServerURI(mqttUrl)
                    .setMessageClassType(Message.class)
                    .build();
            connector.init();
            return connector;
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(connector -> connector.receiveMessage(new MessageCallBack<Message>() {
                    @Override
                    public void onNewMessage(Message message) {
                        mMessage = message;
                        mHandler.sendEmptyMessage(0);
                    }
                }))
                .subscribe();


        HttpHelper.get().fullInfo(this::handleFullInfoResponse);
        HttpHelper.get().tomorrow(this::handleTomorrowResponse);

    }

    private void handleTomorrowResponse(TomorrowForestResponse tomorrowForestResponse) {

        mIvWeatherIcon.setImageResource(CodeTransformer.getWeatherIcon(tomorrowForestResponse.getFlag()));
        mTvWeatherText.setText(tomorrowForestResponse.getDescription());
        mTvWeatherTmp.setText("" + tomorrowForestResponse.getEanTemperature() + "℃");
        mTvWeatherMaxMin.setText("Tmp: ↑" + tomorrowForestResponse.getAxTemperature() + "    ↓" + tomorrowForestResponse.getInTemperature() + "");
        mTvWeatherWind.setText("D: " + tomorrowForestResponse.getWindDirection() + "   S: " + tomorrowForestResponse.getWindForce());

    }

    private Message mMessage;

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            handleServerMessage(mMessage);
        }
    };


    private void handleFullInfoResponse(FullInfoResponse fullInfoResponse) {
        String meanTemp = String.valueOf((int) fullInfoResponse.getDate().getEan_tem());
        mTtWindowTemp.setText(meanTemp + "℃");
        mTtWindowWeatherStat.setText(fullInfoResponse.getDate().getDescription());
        String lightLimit = String.valueOf(((int) fullInfoResponse.getDate().getAx_ill()))
                + "/"
                + String.valueOf(((int) fullInfoResponse.getDate().getIn_ill()));
        mTtWindowAirQuality.setText(lightLimit);
        String tempLimitt = String.valueOf(((int) fullInfoResponse.getDate().getAx_tem()))
                + "/"
                + String.valueOf(((int) fullInfoResponse.getDate().getIn_tem()));
        mTtWindowAirTemp.setText(tempLimitt);
        String humpLimit = String.valueOf(((int) fullInfoResponse.getDate().getAx_hum()))
                + "/"
                + String.valueOf(((int) fullInfoResponse.getDate().getIn_hum()));
        mTtWindowAirHump.setText(humpLimit);
        String windSpead = String.valueOf(fullInfoResponse.getDate().getWind_speed());
        mTvWindSpeed.setText(windSpead);
        mTvWindDirection.setText(fullInfoResponse.getDate().getWind_des());

    }


    /**
     * 来自主服务器的数据
     *
     * @param message
     */
    private void handleServerMessage(Message message) {
        SensorData sensorData = message.getData();
        addChartValue((float) sensorData.getTemperature());
        String tmp = String.valueOf((int) sensorData.getTemperature()) + "℃";
        mTtWindowTemp.setText(tmp);
        String s = String.valueOf(sensorData.getWindSpeedValue()) + "";
        mTvWindSpeed.setText(s);
        mTvWindDirection.setText(sensorData.getWindDirectionText());
        mCToolbar.setTitle("新都 " + tmp);
    }


    @Override
    protected void onStart() {
        super.onStart();
        mVdWeatherVideo.start();
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
        axisY.setMaxLabelChars(5); //默认是3，只能看最后三个数字
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


    /**
     * 获取3天天气预报 网络访问成功后回调
     *
     * @param forecastResponse 返回对象
     */
    private void handleForecastResponse(ForecastResponse forecastResponse) {
        Log.d(TAG, forecastResponse.toString());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        List<FutureDay> futureDays = new ArrayList<>();
        for (int i = 0; i < forecastResponse.getHeWeather5().get(0).getDaily_forecast().size(); i++) {
            futureDays.add(new FutureDay(CodeTransformer.getWeatherIcon(Integer.parseInt(forecastResponse.getHeWeather5().get(0).getDaily_forecast().get(i).getCond().getCode_d())),
                    DateUtils.dayOfWeek2Str(DateUtils.strDayOfWeek(forecastResponse.getHeWeather5().get(0).getDaily_forecast().get(i).getDate())),
                    forecastResponse.getHeWeather5().get(0).getDaily_forecast().get(i).getCond().getTxt_d(),
                    forecastResponse.getHeWeather5().get(0).getDaily_forecast().get(i).getTmp().getAx() + "℃/" +
                            forecastResponse.getHeWeather5().get(0).getDaily_forecast().get(i).getTmp().getIn() + "℃"));
            Log.d("code to int is:", "" + Integer.parseInt(forecastResponse.getHeWeather5().get(0).getDaily_forecast().get(i).getCond().getCode_d()));
        }
        FutureRecyclerAdapter adapter = new FutureRecyclerAdapter(this, futureDays);
        futureRecyclr.setLayoutManager(layoutManager);
        futureRecyclr.setAdapter(adapter);
        adapter.setClickListener((view, position) -> Toast.makeText(MainActivity.this, "点击了第" + position + "个item", Toast.LENGTH_SHORT).show());
        adapter.setLongClickListener((view, position) -> Toast.makeText(MainActivity.this, "长按了第" + position + "个item", Toast.LENGTH_SHORT).show());

        mTtWindowAirTemp.setText(forecastResponse.getHeWeather5().get(0).getDaily_forecast().get(0).getTmp().getAx() + "/" +
                forecastResponse.getHeWeather5().get(0).getDaily_forecast().get(0).getTmp().getIn());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            startActivity(IntentManager.toSettingActivity(this));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
