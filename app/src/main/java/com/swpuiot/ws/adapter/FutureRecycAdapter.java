package com.swpuiot.ws.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swpuiot.ws.clicklistener.MyItemClickListener;
import com.swpuiot.ws.clicklistener.MyItemLongClickListener;
import com.swpuiot.ws.entities.FutureDay;

import java.util.List;

/**
 * Created by 羊荣毅_L on 2017/6/18.
 */
public class FutureRecycAdapter extends RecyclerView.Adapter<FutureRecycHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private MyItemClickListener mClickListener;
    private MyItemLongClickListener mLongClickListener;
    private List<FutureDay> mFutureDays;

    @Override
    public FutureRecycHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(FutureRecycHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
class FutureRecycHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{

    public FutureRecycHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}