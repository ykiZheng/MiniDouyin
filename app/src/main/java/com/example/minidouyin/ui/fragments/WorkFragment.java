package com.example.minidouyin.ui.fragments;

import android.content.Intent;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minidouyin.BaseFragment;
import com.example.minidouyin.PlayListActivity;
import com.example.minidouyin.R;
import com.example.minidouyin.core.recycler.TestData;
import com.example.minidouyin.core.recycler.TestDataSet;
import com.example.minidouyin.core.recycler.adapter.recyclerAdapter;

public class WorkFragment extends BaseFragment implements recyclerAdapter.IOnItemClickListener {

    private RecyclerView recyclerView;
    private recyclerAdapter mAdapter;
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_work;
    }

    @Override
    protected void init() {
        recyclerView = rootView.findViewById(R.id.recyclerview);
        //更改数据时不会变更宽高
        recyclerView.setHasFixedSize(true);
        //设置布局管理器
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        //创建Adapter
        mAdapter = new recyclerAdapter(TestDataSet.getData());
        //设置Adapter每个item的点击事件
        mAdapter.setOnItemClickListen((recyclerAdapter.IOnItemClickListener) this);
        //设置Adapter
        recyclerView.setAdapter(mAdapter);

    }

//    private void loadData() {
//        int[] picIds = new int[] {R.mipmap.ic_launcher, R.mipmap.ic_launcher_round, R.mipmap.cover_three, R.mipmap.cover_four, R.mipmap.head};
//        for (int i=0;i<25;i++) {
//            int pos = (int) (Math.random()*5);
//            datas.add(picIds[pos]);
//        }
//        workAdapter.notifyDataSetChanged();
//    }


    public void onItemCLick(int position, TestData data) {
        getContext().startActivity(new Intent(getContext(), PlayListActivity.class));
    }


}
