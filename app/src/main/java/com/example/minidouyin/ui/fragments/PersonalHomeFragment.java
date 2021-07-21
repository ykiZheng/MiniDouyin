package com.example.minidouyin.ui.fragments;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.minidouyin.BaseFragment;
import com.example.minidouyin.R;
import com.example.minidouyin.core.recycler.adapter.CommPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;

public class PersonalHomeFragment extends BaseFragment {
    @BindView(R.id.vg)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    private FloatingActionButton floatingActionButton;
    private CommPagerAdapter pagerAdapter;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private String[]titles = new String[]{"作品","喜欢"};

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_personal_home;
    }

    @Override
    protected void init() {
        //floatingActionButton = rootView.findViewById(R.id.fab);

        for(int i = 0; i < titles.length;i++){
            fragments.add(new WorkFragment());
        }

        pagerAdapter = new CommPagerAdapter(getChildFragmentManager(),fragments,titles);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("作品");
        tabLayout.getTabAt(1).setText("喜欢");
    }
}