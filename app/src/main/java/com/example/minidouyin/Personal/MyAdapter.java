package com.example.minidouyin.Personal;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minidouyin.API.Message;
import com.example.minidouyin.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Message> mData = new ArrayList<>(); //定义数据源
    private IOnItemClickListener mItemClickListener;

    //定义构造方法，默认传入上下文和数据源
    public MyAdapter() { }

    @Override  //将ItemView渲染进来，创建ViewHolder
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MyViewHolder mHolder = (MyViewHolder) holder;
        Message message = mData.get(position);
        Uri uri = Uri.parse(message.image_url);

        mHolder.image.setImageURI(uri);
        int temy = Integer.parseInt(message.image_h);
        int temx = Integer.parseInt(message.image_w);
        float scale = (float) temy / (float)temx;
        temy = temy / 400 + 1;
        temx = temx / 200 + 1;
        int tem = Math.min(temx, temy);
        if (tem == 0) tem = 1;
        temx = temx * 200 / tem;
        temy = (int)((float)temx * scale);

        mHolder.image.setMinimumHeight(temy);
        mHolder.image.setMinimumWidth(temx);
        mHolder.user.setText("发布人：" + message.getUsername()); //从数据源中获取图片高度，动态设置到控件上
        String temstr = message.updatedAt.substring(0, 10);
        mHolder.exta.setText(message.getExtravalue() +" "+ temstr);
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    public interface IOnItemClickListener {

        void onItemCLick(int position, Message message);
        void onItemLongCLick(int position, Message message);
    }

    public void setOnItemClickListener(IOnItemClickListener listener) {
        mItemClickListener = listener;
    }

    //定义自己的ViewHolder，将View的控件引用在成员变量上
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView user;
        public TextView exta;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.user_avatar);
            user = itemView.findViewById(R.id.user_name);
            exta = itemView.findViewById(R.id.extra_time);
        }
    }

    public void addData(Message message){
        Messageset.addMessage(message);
        mData.add(message);
    }

}