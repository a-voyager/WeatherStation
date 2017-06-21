package com.swpuiot.ws.activities;

import android.view.MenuItem;

import com.swpuiot.ws.R;
import com.swpuiot.ws.base.BaseActivity;

public class LightActivity extends BaseActivity {


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
