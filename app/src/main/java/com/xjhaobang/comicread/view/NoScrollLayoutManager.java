package com.xjhaobang.comicread.view;

import android.content.Context;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

/**
 * Created by PC on 2017/12/17.
 */

public class NoScrollLayoutManager extends StaggeredGridLayoutManager{
    private  boolean isScrollEnabled = true;

    public NoScrollLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public NoScrollLayoutManager(int spanCount, int orientation) {
        super(spanCount, orientation);
    }

    public void setScrollEnabled(boolean scrollEnabled) {
        isScrollEnabled = scrollEnabled;
    }

    @Override
    public boolean canScrollVertically() {
        return  isScrollEnabled && super.canScrollVertically();
    }
}
