package com.xjhaobang.comicread.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.xjhaobang.comicread.R;
import com.xjhaobang.comicread.adapter.PhotoPagerAdapter;
import com.xjhaobang.comicread.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DetailsPhotoActivity<T> extends BaseActivity {
    @BindView(R.id.vp_details_photo)
    ViewPager mVpDetailsPhoto;
    @BindView(R.id.tv_details_photo_count)
    TextView mTvDetailsPhotoCount;
    private PhotoPagerAdapter mPagerAdapter;
    private List<T> mPhotoList;
    private int mIndex = 0;
    private int mSize = 0;

    private static final String PHOTO_LIST = "photo_list";
    private static final String PHOTO_INDEX = "photo_index";

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_details_photo;
    }

    public static <T> Intent newIntent(Context context, int index, ArrayList<T> list) {
        Intent intent = new Intent(context, DetailsPhotoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(PHOTO_LIST, list);
        intent.putExtras(bundle);
        intent.putExtra(PHOTO_INDEX, index);
        return intent;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void initData() {
        mIndex = getIntent().getIntExtra(PHOTO_INDEX, 0);
        mPhotoList = (List<T>) getIntent().getExtras().getSerializable(PHOTO_LIST);
        mSize = mPhotoList.size();
        mPagerAdapter = new PhotoPagerAdapter(this, mPhotoList);
        mVpDetailsPhoto.setAdapter(mPagerAdapter);
    }

    @Override
    protected void initView() {
        mTvDetailsPhotoCount.setText(mIndex + 1 + "/" + mSize);
        mVpDetailsPhoto.setCurrentItem(mIndex);
        mVpDetailsPhoto.setOffscreenPageLimit(2);
    }

    @Override
    protected void initListener() {
        mVpDetailsPhoto.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mIndex = position;
                mTvDetailsPhotoCount.setText(position + 1 + "/" + mSize);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
