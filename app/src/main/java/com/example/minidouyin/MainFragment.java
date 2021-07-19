package com.example.minidouyin;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class MainFragment extends BaseFragment {
    private static String TAG = "MainFragment";

    private CurrentLocationFragment currentLocationFragment;
    private RecommendFragment recommendFragment;
    private ViewPager viewPager;
    private TabLayout tabLayout;

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
        viewPager = rootView.findViewById(R.id.viewpager);
        tabLayout = rootView.findViewById(R.id.tabLayout);

        currentLocationFragment = new CurrentLocationFragment();
        recommendFragment = new RecommendFragment();
        fragments.add(currentLocationFragment);
        fragments.add(recommendFragment);


        pagerAdapter = new CommPagerAdapter(getChildFragmentManager(), fragments, new String[]{"杭州", "推荐"});
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("杭州");
        tabLayout.getTabAt(1).setText("推荐");
    }

}
