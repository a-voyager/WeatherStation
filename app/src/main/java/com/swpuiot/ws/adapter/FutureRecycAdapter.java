package com.swpuiot.ws.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.swpuiot.ws.R;
import com.swpuiot.ws.clicklistener.MyItemClickListener;
import com.swpuiot.ws.clicklistener.MyItemLongClickListener;
import com.swpuiot.ws.entities.FutureDay;

import java.util.List;

import butterknife.BindView;
import top.wuhaojie.lib.image.ImageLoader;

/**
 * Created by 羊荣毅_L on 2017/6/18.
 */
public class FutureRecycAdapter extends RecyclerView.Adapter<FutureRecycHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private MyItemClickListener mClickListener;
    private MyItemLongClickListener mLongClickListener;
    private List<FutureDay> mFutureDays;

    public FutureRecycAdapter(Context context, List<FutureDay> futureDays) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mFutureDays = futureDays;
    }

    @Override
    public FutureRecycHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_future_weather, parent, false);
        FutureRecycHolder holder = new FutureRecycHolder(itemView, mClickListener, mLongClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(FutureRecycHolder holder, int position) {
        holder.weatherLogo.setImageResource(mFutureDays.get(position).getImageName());
        holder.weekday.setText(mFutureDays.get(position).getWeekday());
        holder.stat.setText(mFutureDays.get(position).getDayWeather());
        holder.temp.setText(mFutureDays.get(position).getDayTemp());
    }

    @Override
    public int getItemCount() {
        return mFutureDays.size();
    }

    //暴露给Recycleview设置点击事件
    public void setClickListener(MyItemClickListener clickListener) {
        mClickListener = clickListener;
    }

    //暴露给Recycleview设置长按点击事件
    public void setLongClickListener(MyItemLongClickListener longClickListener) {
        mLongClickListener = longClickListener;
    }
}

class FutureRecycHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    public ImageView weatherLogo;
    public TextView weekday;
    public TextView stat;
    public TextView temp;
    public MyItemClickListener mClickListener;
    public MyItemLongClickListener mLongClickListener;

    public FutureRecycHolder(View itemView, MyItemClickListener clickListener, MyItemLongClickListener longClickListener) {
        super(itemView);
        weatherLogo = (ImageView) itemView.findViewById(R.id.img_weather_logo);
        weekday= (TextView) itemView.findViewById(R.id.tv_weekday);
        stat= (TextView) itemView.findViewById(R.id.tv_weather_air_stat);
        temp= (TextView) itemView.findViewById(R.id.tv_weekday_temp);
        mClickListener = clickListener;
        mLongClickListener = longClickListener;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (itemView != null) {
            mClickListener.onItemClick(v, getPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (itemView != null) {
            mLongClickListener.onItemLongClick(v, getPosition());
        }
        return false;
    }
}