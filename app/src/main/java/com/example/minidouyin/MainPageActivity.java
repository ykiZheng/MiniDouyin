package com.example.minidouyin;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.minidouyin.API.Constants;
import com.example.minidouyin.base.BaseActivity;
import com.example.minidouyin.core.recycler.adapter.CommPagerAdapter;

import java.util.ArrayList;

import butterknife.BindView;

public class MainPageActivity extends BaseActivity {
    @BindView(R.id.vg)
    ViewPager viewPager;

    private CommPagerAdapter pageAdapter;
    private ArrayList<Fragment> fragments = new ArrayList<>();

    private String user_name = Constants.studentName;
    private String user_ID = Constants.stduentID;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_main_page;
    }

    @Override
    protected void init() {

        MainActivity.personalHome = new PersonalHomeFragment();
        MainActivity.personalHome.setuser(user_name, user_ID);
        MainFragment mainfragment = new MainFragment();

        fragments.add(mainfragment);
        fragments.add(MainActivity.personalHome);
        pageAdapter = new CommPagerAdapter(getSupportFragmentManager(), fragments, new String[]{"", ""});
        viewPager.setAdapter(pageAdapter);
    }

}