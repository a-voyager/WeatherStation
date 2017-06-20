package com.swpuiot.ws.activities;


import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.swpuiot.ws.R;
import com.swpuiot.ws.base.BaseActivity;


public class SettingsActivity extends BaseActivity {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        getFragmentManager().beginTransaction()
                .replace(R.id.fl_setting, InnerFragment.newInstance())
                .commit();
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_settings;
    }

    public static class InnerFragment extends PreferenceFragment {

        public static InnerFragment newInstance() {
            return new InnerFragment();
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_setting);
        }


    }
}
