package com.swpuiot.ws.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.swpuiot.ws.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2017/06/19 17:21
 * Version: 1.0
 */

public class HourlyListAdapter extends RecyclerView.Adapter {


    private Context mContext;
    private final LayoutInflater mInflater;

    public HourlyListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public static class Item {
        public String date;
        public String condCode;
        public String condTxt;
        public String hum;
        public String tmp;
        public String press;
        public String wind_dir;
        public String wind_sc;

    }

    private List<Item> mList = new ArrayList<>();

    public void setList(List<Item> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_hourly_weather, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((Holder) holder).setView(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class Holder extends RecyclerView.ViewHolder {


        @BindView(R.id.tv_date)
        TextView mTvDate;
        @BindView(R.id.iv_icon)
        ImageView mIvIcon;
        @BindView(R.id.tv_cond)
        TextView mTvCond;
        @BindView(R.id.tv_tmp)
        TextView mTvTmp;
        @BindView(R.id.tv_wind_dir)
        TextView mTvWindDir;
        @BindView(R.id.tv_hum)
        TextView mTvHum;
        @BindView(R.id.tv_wind_sc)
        TextView mTvWindSc;

        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setView(Item item) {
            // TODO: 17-6-19 设置单项 UI
        }

    }

}
