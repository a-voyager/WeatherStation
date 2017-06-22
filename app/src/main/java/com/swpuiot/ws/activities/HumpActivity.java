package com.swpuiot.ws.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.swpuiot.ws.R;
import com.swpuiot.ws.base.BaseActivity;
import com.swpuiot.ws.data.HttpHelper;
import com.swpuiot.ws.entities.response.FullInfoResponse;

import butterknife.BindView;

public class HumpActivity extends BaseActivity {
    @BindView(R.id.tv_hump_limit)
    TextView humpLimit;
    @BindView(R.id.tv_hump_circular_value)
    TextView humpValue;


    @Override
    protected void initViews() {
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HttpHelper.get().fullInfo(this::handleFullInfoResponse);
    }

    private void handleFullInfoResponse(FullInfoResponse fullInfoResponse) {
        String humptext = String.valueOf(((int) fullInfoResponse.getDate().getAx_hum()))
                + "/"
                + String.valueOf(((int) fullInfoResponse.getDate().getIn_hum()))+"RH";
        humpLimit.setText(humptext);

        humpValue.setText(String.valueOf(((int) fullInfoResponse.getDate().getEan_hum())));
    }


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_hump;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }
}
