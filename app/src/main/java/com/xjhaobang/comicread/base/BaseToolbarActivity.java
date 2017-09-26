package com.xjhaobang.comicread.base;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.xjhaobang.comicread.R;


public abstract class BaseToolbarActivity extends BaseActivity {

    private Toolbar mToolbar;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        initToolbar();
    }

    private void initToolbar() {
        mToolbar = $(R.id.include_toolbar);
        if (mToolbar == null) {
            throw new IllegalStateException("No Toolbar");
        }
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");
    }

    @Override
    public void setTitle(CharSequence title) {
        mToolbar.setTitle(title);
    }

    @Override
    public void setTitle(int resId) {
        mToolbar.setTitle(resId);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
