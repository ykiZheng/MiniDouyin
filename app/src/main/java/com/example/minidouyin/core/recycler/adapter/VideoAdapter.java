package com.example.minidouyin.core.recycler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minidouyin.R;
import com.example.minidouyin.VideoInfo;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.HomeViewHolder> {

    private Context mContext;
    private List<VideoInfo> homeList = new ArrayList<>();

    public VideoAdapter(Context context, List<VideoInfo> datas) {
        this.mContext = context;
        this.homeList = homeList;
    }
    //建立列表元件
    @NonNull
    @Override
    public VideoAdapter.HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item,null);
        return new VideoAdapter.HomeViewHolder(view,this);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.HomeViewHolder holder, int position) {
        holder.imageIdIv.setImageResource(homeList.get(position).getImageId());
        holder.nameTv.setText(homeList.get(position).getName());
    }

    //返回列表資料總數
    @Override
    public int getItemCount() {
        return homeList.size();
    }


    public class HomeViewHolder extends RecyclerView.ViewHolder{
        TextView nameTv;
        ImageView imageIdIv;
        private RecyclerView.Adapter adapter;
        public HomeViewHolder(@NonNull View itemView,RecyclerView.Adapter adapter) {
            super(itemView);
            this.nameTv = itemView.findViewById(R.id.home_item_text);
            this.imageIdIv = itemView.findViewById(R.id.home_item_mage);
            this.adapter = adapter;
        }
    }
}
