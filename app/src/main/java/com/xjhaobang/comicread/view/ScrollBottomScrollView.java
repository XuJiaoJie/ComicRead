package com.xjhaobang.comicread.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.xjhaobang.comicread.listener.ScrollBottomListener;

/**
 * Created by PC on 2017/12/17.
 */

public class ScrollBottomScrollView extends ScrollView{
    private ScrollBottomListener mScrollBottomListener;
    private boolean isCompete = true;

    public ScrollBottomScrollView(Context context) {
        super(context);
    }

    public ScrollBottomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollBottomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (isCompete && t + getHeight() >= computeVerticalScrollRange()){
            isCompete = false;
            mScrollBottomListener.scrollBottom();
        }
        super.onScrollChanged(l, t, oldl, oldt);
    }


    public void setScrollBottomListener(ScrollBottomListener scrollBottomListener){
        mScrollBottomListener = scrollBottomListener;
    }

    public void onBottomCompete(){
        isCompete = true;
    }

    public void loadBottomOver(){
        isCompete = false;
    }

}
