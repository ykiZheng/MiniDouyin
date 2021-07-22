package com.example.minidouyin.Recommend;

import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minidouyin.API.Message;
import com.example.minidouyin.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.VideoViewHolder> {
    private List<Message> data; //定义数据源
    private IOnItemClickListener mItemClickListener;

    public void setData(List<Message> messageList){
        data = messageList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override  //将ItemView渲染进来，创建ViewHolder
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        return new VideoViewHolder(root);
    }

    @Override  //将数据源的数据绑定到相应控件上
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.bind(data.get(position));
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemCLick(position, data.get(position));
                }
            }
        });
        holder.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemLongCLick(position, data.get(position));
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public interface IOnItemClickListener {

        void onItemCLick(int position, Message message);
        void onItemLongCLick(int position, Message message);
    }

    public void setOnItemClickListener(IOnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder{
        private SimpleDraweeView image;
        private TextView user;
        private TextView exta;
        private View contentView;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            contentView = itemView;
            image = itemView.findViewById(R.id.user_avatar);
            user = itemView.findViewById(R.id.user_name);
            exta = itemView.findViewById(R.id.extra_time);
        }
        public void bind(Message message){
            int temy = Integer.parseInt(message.image_h);
            int temx = Integer.parseInt(message.image_w);

            DisplayMetrics metrics = new DisplayMetrics();
            float scale = (float) temy / (float)temx;
            temy = temy / 400 + 1;
            temx = temx / 200 + 1;
            int tem = Math.min(temx, temy);
            if (tem == 0) tem = 1;
            temx = temx * 200 / tem;
            temy = (int)((float)temx * scale);

            image.setMinimumHeight(temy);
            image.setMinimumWidth(temx);
            image.setImageURI(Uri.parse(message.getCoverimage()));
            user.setText("发布人: " + message.getUsername());
            String temstr = message.updatedAt.substring(0, 10);
            exta.setText(message.getExtravalue() +" "+ temstr);
        }

        public void setOnClickListener(View.OnClickListener listener) {
            if (listener != null) {
                contentView.setOnClickListener(listener);
            }
        }

        public void setOnLongClickListener(View.OnLongClickListener listener) {
            if (listener != null) {
                contentView.setOnLongClickListener(listener);
            }
        }
    }

}