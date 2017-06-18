package com.swpuiot.ws.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.swpuiot.ws.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2017/06/18 13:32
 * Version: 1.0
 */

public abstract class BaseActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResID());
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
    }

    protected abstract int getLayoutResID();
}
