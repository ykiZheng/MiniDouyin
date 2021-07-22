package com.example.minidouyin;


import com.example.minidouyin.base.BaseActivity;

public class PlayListActivity extends BaseActivity {


    @Override
    protected int setLayoutId() {
        return R.layout.activity_play_list;
    }

    @Override
    protected void init() {
        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new RecommendFragment()).commit();
    }
}
