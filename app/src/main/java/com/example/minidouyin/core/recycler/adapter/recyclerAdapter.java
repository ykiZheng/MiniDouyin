package com.example.minidouyin.core.recycler.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.minidouyin.R;
import com.example.minidouyin.core.recycler.TestData;

import java.util.List;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.ViewHolder> {


    public static final String TAG = "recyclerListAdapter";
    private IOnItemClickListener mItemClickListener;

    private List<TestData> mList;
    //private OnRecyclerTouchListener mOnRecyclerTouchListener;


    public recyclerAdapter(List<TestData> testList) {
        mList = testList;
    }


    @Override
    public recyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_recycler_item, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.onBind(position, mList.get(position));
        holder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(mItemClickListener != null){
                    mItemClickListener.onItemCLick(position, mList.get(position));
                }
            }
        });
    }


    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
    }


    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }



    public void setOnItemClickListen(IOnItemClickListener listener){
        mItemClickListener = listener;
    }

    public void addData(int position, TestData data) {
        mList.add(position, data);
        notifyItemInserted(position);
        if (position != mList.size()) {
            //刷新改变位置item下方的所有Item的位置,避免索引错乱
            notifyItemRangeChanged(position, mList.size() - position);
        }
    }

    public interface IOnItemClickListener {
        public void onItemCLick(int position, TestData data) ;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder  {

        TextView titleView;
        View contentView;


        public ViewHolder(View itemView) {
            super(itemView);
            contentView = itemView;
            titleView = itemView.findViewById(R.id.tv_title);
        }
        public void onBind(int position, TestData data){
            titleView.setText(data.getTitle());
        }


        public void setOnClickListener(View.OnClickListener listener) {
            if(listener != null){
                contentView.setOnClickListener(listener);
            }
        }
    }
}
