package com.example.minidouyin;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class ScrollableViewPager extends ViewPager {

    private boolean scrollable = true;
    private float startX;

    public ScrollableViewPager(@NonNull Context context) {
        super(context);
    }

    public ScrollableViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(!scrollable){
            return false;
        }
        return super.onTouchEvent(ev);
    }

    public boolean isCanScrollble() {
        return scrollable;
    }

    public void setCanScrollble(boolean scrollble) {
        this.scrollable = scrollble;
    }
}
