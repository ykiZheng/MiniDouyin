package com.example.minidouyin.ui.fragments;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.minidouyin.BaseFragment;
import com.example.minidouyin.R;
import com.example.minidouyin.VideoInfo;
import com.example.minidouyin.core.recycler.adapter.VideoAdapter;

import java.util.ArrayList;

public class RecommendFragment extends BaseFragment {
//    @BindView(R.id.recyclerview)
    private RecyclerView recyclerView;
    private ArrayList<VideoInfo> datas = new ArrayList<>();
    private VideoAdapter adapter;

    private String url; //当前视频url

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void init() {
        recyclerView = rootView.findViewById(R.id.recyclerview);
        loadData();
        adapter = new VideoAdapter(this.getActivity(),datas);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));


        //创建分割线对象
        recyclerView.addItemDecoration(new MyDecoration());

        recyclerView.setAdapter(adapter);

    }

    private void loadData(){
        String[] names = new String[]{"車位查詢","車位預定","與車間距","防盜警報","停車時長","車位查詢","車位預定","與車間距","防盜警報","停車時長"};
        int[] ImageId = new int[]{R.drawable.car1,R.drawable.car2,R.drawable.car3,R.drawable.car4,R.drawable.car5,R.drawable.car1,R.drawable.car2,R.drawable.car3,R.drawable.car4,R.drawable.car5};
        for(int i = 0 ;i < names.length ; i++){
            this.datas.add(new VideoInfo(names[i],ImageId[i]));
        }
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
