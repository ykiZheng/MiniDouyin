package com.example.minidouyin;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;


public class MainFragment extends BaseFragment {
    private static String TAG = "MainFragment";

    private CurrentLocationFragment currentLocationFragment;
    private RecommendFragment recommendFragment;

    @BindView(R.id.vg)
    ViewPager viewPager;
    @BindView(R.id.tabTitle)
    TabLayout tabTitle;

    private String[] titles = new String[]{"杭州","推荐"};

    // TODO: Rename and change types of parameters
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private CommPagerAdapter pagerAdapter;

    @Override
    protected int setLayoutId() {
        Log.d(TAG, "setLayoutId");
        return R.layout.fragment_main;
    }

    @Override
    protected void init() {
        // Required empty public constructor

        setFragments();
    }

    private void setFragments(){
        currentLocationFragment = new CurrentLocationFragment();
        recommendFragment = new RecommendFragment();

        fragments.add(currentLocationFragment);
        fragments.add(recommendFragment);

        tabTitle.addTab(tabTitle.newTab().setText("杭州"));
        tabTitle.addTab(tabTitle.newTab().setText("推荐"));

        pagerAdapter = new CommPagerAdapter(getChildFragmentManager(), fragments, new String[]{"杭州", "推荐"});
        viewPager.setAdapter(pagerAdapter);
        tabTitle.setupWithViewPager(viewPager);
        tabTitle.getTabAt(0).select();
        tabTitle.getTabAt(1).select();

    }

}
