package com.xjhaobang.comicread.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by PC on 2017/9/29.
 */

public class CategoryFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;
    private List<String> mStringList;


    public CategoryFragmentAdapter(FragmentManager fm,List<Fragment> fragmentList,List<String> stringList) {
        super(fm);
        mFragmentList = fragmentList;
        mStringList = stringList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mStringList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mStringList.get(position);
    }


}
