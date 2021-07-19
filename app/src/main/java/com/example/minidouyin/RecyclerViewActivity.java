package com.example.minidouyin;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.minidouyin.core.recycler.TestData;
import com.example.minidouyin.core.recycler.TestDataSet;
import com.example.minidouyin.core.recycler.recyclerAdapter;

public class RecyclerViewActivity extends AppCompatActivity implements recyclerAdapter.IOnItemClickListener{
    private static final String TAG =  "RecyclerViewActivity";
    private RecyclerView recyclerView;
    private recyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        Log.i(TAG,"onCreate");
        initView();
    }

    private void initView(){
        //获取实例
        recyclerView = findViewById(R.id.rv);
        //更改数据时不会变更宽高
        recyclerView.setHasFixedSize(true);
        //设置布局管理器
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        //创建Adapter
        mAdapter = new recyclerAdapter(TestDataSet.getData());
        //设置Adapter每个item的点击事件
        mAdapter.setOnItemClickListen((recyclerAdapter.IOnItemClickListener) this);
        //设置Adapter
        recyclerView.setAdapter(mAdapter);
        //设置分割线


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    public void onItemCLick(int position, TestData data) {
        Toast.makeText(RecyclerViewActivity.this, "点击了第" + position + "条", Toast.LENGTH_SHORT).show();
        mAdapter.addData(position + 1, new TestData("新增头条"));
    }
}
