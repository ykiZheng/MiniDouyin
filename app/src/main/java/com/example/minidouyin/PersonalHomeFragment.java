package com.example.minidouyin;

import android.os.Bundle;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.minidouyin.API.Message;

import com.example.minidouyin.Personal.MyvideoFragment;
import com.example.minidouyin.R;
import com.example.minidouyin.base.BaseFragment;
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

    // 账号名称
    @BindView(R.id.tvName)
    TextView tvTitle;

    // ID

    private FloatingActionButton floatingActionButton;
    public CommPagerAdapter pagerAdapter;
    public ArrayList<Fragment> fragments = new ArrayList<>();
    private String[]titles = new String[]{"作品", "收藏"};

    private String user_name;
    private String user_id;

    public CurrentLocationFragment Myvideo;
    public MyvideoFragment Mylike;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_personal_home;
    }

    @Override
    protected void init() {
        //floatingActionButton = rootView.findViewById(R.id.fab);

        Myvideo = new CurrentLocationFragment();
        Mylike = new MyvideoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("ID", user_id);
        Myvideo.setArguments(bundle);
        fragments.add(Myvideo);
        fragments.add(Mylike);

        pagerAdapter = new CommPagerAdapter(getChildFragmentManager(), fragments, titles);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("作品");
        tabLayout.getTabAt(1).setText("收藏");

        tvTitle.setText(user_name);
    }

    public void setuser(String user_name, String user_id){
        this.user_name = user_name;
        this.user_id = user_id;
    }


    public void add_like(Message message){
        Mylike.AddData(message);
    }
}
