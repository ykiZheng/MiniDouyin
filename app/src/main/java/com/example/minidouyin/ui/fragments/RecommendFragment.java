package com.example.minidouyin.ui.fragments;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minidouyin.BaseFragment;
import com.example.minidouyin.R;
import com.example.minidouyin.core.recycler.adapter.VideoAdapter;

import java.util.ArrayList;

public class RecommendFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private ArrayList<String> datas = new ArrayList<>();
    private VideoAdapter adapter;


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void init() {
        recyclerView = rootView.findViewById(R.id.recyclerview);

        adapter = new VideoAdapter(this.getActivity(),datas);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //创建分割线对象
//        recyclerView.addItemDecoration(new MyDecoration());

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        loadData();

    }

    private void loadData(){
        for (int i = 0; i < 3; i++) {
            datas.add("");
        }
        adapter.notifyDataSetChanged();
    }

    //分割线的类
    class MyDecoration extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){
            super.getItemOffsets(outRect,view ,parent,state);
            int gap = getResources().getDimensionPixelSize(R.dimen.fab_margin);//5dp
            outRect.set(gap,gap,gap,gap);
        }
    }

}
