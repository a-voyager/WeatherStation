package com.swpuiot.ws.activities;

import android.view.MenuItem;
import android.widget.TextView;

import com.swpuiot.ws.R;
import com.swpuiot.ws.base.BaseActivity;

import butterknife.BindView;

public class LightActivity extends BaseActivity {
    @BindView(R.id.tv_light_grade)
    TextView lightGrade;
    @BindView(R.id.tv_light_value)
    TextView lightValue;
    @BindView(R.id.tv_light_out)
    TextView out;
    @BindView(R.id.tv_light_circular_value)
    TextView light;
    @BindView(R.id.tv_light_grade_stat)
    TextView lightGradeStat;
    @BindView(R.id.tv_light_one_ago_value)
    TextView oneValue;
    @BindView(R.id.tv_light_one_ago_stat)
    TextView oneStat;
    @BindView(R.id.tv_light_two_ago_value)
    TextView twoValue;
    @BindView(R.id.tv_light_two_ago_stat)
    TextView twoStat;
    @BindView(R.id.tv_light_three_ago_value)
    TextView threeValue;
    @BindView(R.id.tv_light_three_ago_stat)
    TextView threeStat;

    @Override
    protected void initViews() {
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_light;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }
}
