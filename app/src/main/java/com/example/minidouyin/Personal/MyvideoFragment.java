package com.example.minidouyin.Personal;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.minidouyin.API.Message;
import com.example.minidouyin.R;
import com.example.minidouyin.base.BaseFragment;
import com.facebook.drawee.backends.pipeline.Fresco;

import butterknife.BindView;

public class MyvideoFragment extends BaseFragment {

    private RecyclerView.LayoutManager mLayoutManager;
    private MyAdapter adapter = new MyAdapter();

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_current_location;
    }

    @BindView(R.id.recycler_current)
    RecyclerView mRecyclerView;

    @Override
    protected void init() {
        Fresco.initialize(getContext());
        // getView().setContentView(R.layout.activity_recommend);

        LoadData();

        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);

    }

    //分割线的类
    class MyDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int gap = getResources().getDimensionPixelSize(R.dimen.fab_margin);//5dp
            outRect.set(gap, gap, gap, gap);
        }
    }

    protected void LoadData(){

        int i;
        if (Messageset.size() == 0) return;
        for(i = 0; i < Messageset.size(); i++){
            adapter.addData(Messageset.messageset.get(i));
        }

    }

    public void AddData(Message message){

        adapter.addData(message);
    }

}