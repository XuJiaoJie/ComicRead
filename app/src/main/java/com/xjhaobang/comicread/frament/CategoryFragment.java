package com.xjhaobang.comicread.frament;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.xjhaobang.comicread.R;
import com.xjhaobang.comicread.adapter.CategoryFragmentAdapter;
import com.xjhaobang.comicread.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by PC on 2017/9/27.
 */

public class CategoryFragment extends BaseFragment {
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private List<Fragment> mFragmentList;
    private List<String> mTitleList;
    private CategoryFragmentAdapter mAdapter;
    private String[] mStrings = {"全部","爆笑","热血","冒险","恐怖","科幻","魔幻","玄幻",
            "校园", "悬疑", "推理", "萌系","穿越", "后宫"};

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_category;
    }

    @Override
    protected void initData(Bundle bundle) {
        mFragmentList = new ArrayList<>();
        mTitleList = new ArrayList<>();
        for (int i=0;i<14;i++){
            CategoryContentFragment fragment = CategoryContentFragment.newInstance(i);
            mFragmentList.add(fragment);
            mTitleList.add(mStrings[i]);
            mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(i)));
        }
    }

    @Override
    protected void initView() {
        mAdapter = new CategoryFragmentAdapter(mBaseActivity.getSupportFragmentManager(),mFragmentList,mTitleList);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void setListener() {

    }


}
