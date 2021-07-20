package com.example.minidouyin.newtwork.model;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.minidouyin.BaseActivity;
import com.example.minidouyin.CommPagerAdapter;
import com.example.minidouyin.MainFragment;
import com.example.minidouyin.PersonalHomeFragment;
import com.example.minidouyin.R;

import java.util.ArrayList;

import butterknife.BindView;

public class MainPageActivity extends BaseActivity {
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
