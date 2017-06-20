package com.swpuiot.ws.adapter;

import android.content.Context;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.swpuiot.ws.R;
import com.swpuiot.ws.entities.Advise;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 羊荣毅_L on 2017/6/20.
 */
public class AdviseAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private final LayoutInflater mInflater;
    public List<Advise> mAdvises;

    public AdviseAdapter(Context context, List<Advise> advises) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mAdvises = advises;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_advise, parent, false);
        return new AdviseHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((AdviseHolder)holder).getView(mAdvises.get(position));
    }

    @Override
    public int getItemCount() {
        return mAdvises.size();
    }

    class AdviseHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_advise_title)
        TextView title;
        @BindView(R.id.tv_advise_content)
        TextView content;
        @BindView(R.id.img_advise_logo)
        ImageView logo;

        public AdviseHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        public void getView(Advise advise){
            logo.setImageResource(advise.getImageId());
            title.setText(advise.getTitle());
            content.setText(advise.getContent());
        }
    }
}
