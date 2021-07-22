package com.example.minidouyin;

import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.minidouyin.API.Constants;
import com.example.minidouyin.base.BaseActivity;
import com.example.minidouyin.core.recycler.adapter.CommPagerAdapter;

import java.util.ArrayList;

import butterknife.BindView;

import static com.example.minidouyin.CustomCameraActivity.REQUEST_UPLOAD;
import static com.example.minidouyin.MainActivity.LOGIN_ANONIMAL_CODE;

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


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == REQUEST_UPLOAD){
//            ;String returnPath = data.getStringExtra("path");
//            if(returnPath != null){
//                fragments.remove(1);
//                fragments.remove(0);
//                init();
//
//            }
//        }
//        else if(requestCode == LOGIN_ANONIMAL_CODE){
//            Constants.studentName = null;
//            Constants.stduentID = null;
//            fragments.remove(1);
//            fragments.remove(0);
//            init();
//        }
//    }

}
