package com.example.minidouyin;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.minidouyin.Utils.PauseVideoEvent;
import com.example.minidouyin.Utils.RxBus;
import com.example.minidouyin.base.BaseFragment;
import com.example.minidouyin.core.recycler.adapter.CommPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;

import static com.example.minidouyin.CustomCameraActivity.REQUEST_UPLOAD;


public class MainFragment extends BaseFragment{
    private static String TAG = "MainFragment";

    private CurrentLocationFragment currentLocationFragment;
    private RecommendFragment recommendFragment;

    @BindView(R.id.vg)
    ViewPager viewPager;
    @BindView(R.id.tabTitle)
    TabLayout tabTitle;
    @BindView(R.id.ig_record)
    ImageView ig_record;

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

        ig_record.setOnClickListener(v -> {
            Context mContext = rootView.getContext();
            Intent intent = new Intent(mContext, CustomCameraActivity.class);
            startActivity(intent);
        });

        setFragments();
    }

    private void setFragments(){
        currentLocationFragment = new CurrentLocationFragment();
        recommendFragment = new RecommendFragment();

        fragments.add(currentLocationFragment);
        fragments.add(recommendFragment);


        pagerAdapter = new CommPagerAdapter(getChildFragmentManager(), fragments, new String[]{"杭州", "推荐"});
        viewPager.setAdapter(pagerAdapter);
        tabTitle.setupWithViewPager(viewPager);
        tabTitle.getTabAt(0).setText("杭州");
        tabTitle.getTabAt(1).setText("推荐");
        tabTitle.getTabAt(1).select();


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 1){
                    //继续播放
                    RxBus.getDefault().post(new PauseVideoEvent(true));
                }
                else
                {
                    RxBus.getDefault().post(new PauseVideoEvent(false));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }



}
