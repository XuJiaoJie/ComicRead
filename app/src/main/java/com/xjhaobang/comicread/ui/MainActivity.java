package com.xjhaobang.comicread.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.xjhaobang.comicread.R;
import com.xjhaobang.comicread.base.BaseActivity;
import com.xjhaobang.comicread.frament.CategoryFragment;
import com.xjhaobang.comicread.frament.HotFragment;
import com.xjhaobang.comicread.frament.MainFragment;
import com.xjhaobang.comicread.frament.SearchFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.fl_layout)
    FrameLayout mFlLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle mToggle;
    private MainFragment mMainFragment;
    private CategoryFragment mCategoryFragment;
    private HotFragment mHotFragment;
    private SearchFragment mSearchFragment;
    private FragmentManager mFragmentManager;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        mFragmentManager = getSupportFragmentManager();
        if (mMainFragment == null){
            mMainFragment = new MainFragment();
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.add(R.id.fl_layout,mMainFragment);
            transaction.commit();
        }
    }

    @Override
    protected void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("漫画阅读");
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, 0, 0);
        mDrawerLayout.addDrawerListener(mToggle);
        /*同步drawerlayout的状态*/
        mToggle.syncState();
    }

    @Override
    protected void initListener() {
        mNavView.setNavigationItemSelectedListener(this);
        mNavView.setItemIconTintList(null);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        switch (item.getItemId()) {
            case R.id.nav_main:
                hideAllFragment(fragmentTransaction);
                if (mMainFragment == null){
                    mMainFragment = new MainFragment();
                    fragmentTransaction.add(R.id.fl_layout,mMainFragment);
                }else {
                    fragmentTransaction.show(mMainFragment);
                }
                getSupportActionBar().setTitle("漫画阅读");
                break;
            case R.id.nav_category:
                hideAllFragment(fragmentTransaction);
                if (mCategoryFragment == null){
                    mCategoryFragment = new CategoryFragment();
                    fragmentTransaction.add(R.id.fl_layout,mCategoryFragment);
                }else {
                    fragmentTransaction.show(mCategoryFragment);
                }
                getSupportActionBar().setTitle("分类漫画");
                break;
            case R.id.nav_hot:
                hideAllFragment(fragmentTransaction);
                if (mHotFragment == null){
                    mHotFragment = new HotFragment();
                    fragmentTransaction.add(R.id.fl_layout,mHotFragment);
                }else {
                    fragmentTransaction.show(mHotFragment);
                }
                getSupportActionBar().setTitle("热门漫画");
                break;
            case R.id.nav_search:
                hideAllFragment(fragmentTransaction);
                if (mSearchFragment == null){
                    mSearchFragment = new SearchFragment();
                    fragmentTransaction.add(R.id.fl_layout,mSearchFragment);
                }else {
                    fragmentTransaction.show(mSearchFragment);
                }
                getSupportActionBar().setTitle("搜索漫画");
                break;
            case R.id.about:
                showToast("暂无界面");
                break;
            case R.id.more:
                showToast("暂无界面");
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        fragmentTransaction.commit();
        return true;
    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if (mMainFragment != null){
            fragmentTransaction.hide(mMainFragment);
        }
        if (mCategoryFragment != null){
            fragmentTransaction.hide(mCategoryFragment);
        }
        if (mHotFragment != null){
            fragmentTransaction.hide(mHotFragment);
        }
        if (mSearchFragment != null){
            fragmentTransaction.hide(mSearchFragment);
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return;
        }
        super.onBackPressed();
    }
}
