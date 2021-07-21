package com.example.minidouyin;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.example.minidouyin.core.recycler.TestData;
import com.example.minidouyin.core.recycler.TestDataSet;
import com.example.minidouyin.core.recycler.adapter.CommPagerAdapter;
import com.example.minidouyin.core.recycler.adapter.recyclerAdapter;
import com.example.minidouyin.ui.fragments.MainFragment;
import com.example.minidouyin.ui.fragments.PersonalHomeFragment;

import java.util.ArrayList;

import butterknife.BindView;

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

    public static class MainPageActivity extends BaseActivity {
        @BindView(R.id.vg)
        ViewPager viewPager;

        private CommPagerAdapter pageAdapter;
        private ArrayList<Fragment> fragments = new ArrayList<>();

        @Override
        protected int setLayoutId() {
            return R.layout.activity_main_page;
        }

        @Override
        protected void init() {

            fragments.add(new MainFragment());
            fragments.add(new PersonalHomeFragment());
            pageAdapter = new CommPagerAdapter(getSupportFragmentManager(), fragments,new String[]{"",""});
            viewPager.setAdapter(pageAdapter);
        }
    }
}
