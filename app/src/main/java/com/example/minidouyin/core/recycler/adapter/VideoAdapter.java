package com.example.minidouyin.core.recycler.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;

import com.example.minidouyin.R;
import com.example.minidouyin.base.BaseRvAdapter;
import com.example.minidouyin.base.MyBaseViewHolder;

import java.net.URI;
import java.util.List;

public class VideoAdapter extends BaseRvAdapter<String, VideoAdapter.VideoViewHolder> {


    public VideoAdapter(Context context, List<String> datas) {

        super(context,datas);
    }
    //建立列表元件
    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video,parent,false);
        return new VideoViewHolder(view);
    }


    @Override
    protected void onBindData(VideoViewHolder holder, String data, int position) {
        holder.videoView.setVideoURI(Uri.parse(data));
        holder.videoView.setMediaController(new MediaController(context));
        holder.videoView.start();
    }


    public class VideoViewHolder extends MyBaseViewHolder {
        VideoView videoView;
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoview);
        }
    }
}
