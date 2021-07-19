package com.example.minidouyin.newtwork.model;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.minidouyin.BaseActivity;
import com.example.minidouyin.CommPagerAdapter;
import com.example.minidouyin.MainFragment;
import com.example.minidouyin.R;

import java.util.ArrayList;

public class MainPageActivity extends BaseActivity {

    private ViewPager viewPager;
    private CommPagerAdapter pageAdapter;
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main_page;
    }

    @Override
    protected void init() {

        viewPager = findViewById(R.id.vg);
        fragments.add(new MainFragment());
        pageAdapter = new CommPagerAdapter(getSupportFragmentManager(), fragments,new String[]{"",""});
        viewPager.setAdapter(pageAdapter);
    }
}
