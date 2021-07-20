package com.example.minidouyin;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;


public abstract class BaseFragment extends Fragment {
    private static String TAG = "BaseFragment";

    protected View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        rootView =  inflater.inflate(setLayoutId(), container, false);
        Log.d(TAG,"onCreateView");
        ButterKnife.bind(this,rootView);
        init();
        return rootView;
    }

    protected abstract int setLayoutId();

    protected abstract void init();
}
