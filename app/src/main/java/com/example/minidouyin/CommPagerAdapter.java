package com.example.minidouyin;

import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class CommPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<? extends Fragment> items;
    private String[] mTitles;


    public CommPagerAdapter(FragmentManager fm,ArrayList<? extends Fragment>items, String[] mtitles) {
        super(fm);
        this.items = items;
        this.mTitles = mtitles;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }

    ;

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

}
