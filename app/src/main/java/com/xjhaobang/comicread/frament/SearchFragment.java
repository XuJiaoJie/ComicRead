package com.xjhaobang.comicread.frament;

import android.os.Bundle;

import com.xjhaobang.comicread.R;
import com.xjhaobang.comicread.base.BaseFragment;
import com.xjhaobang.comicread.model.Model;

/**
 * Created by PC on 2017/9/27.
 */

public class SearchFragment extends BaseFragment {
    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initData(Bundle bundle) {
        Model model = new Model();
        model.test();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }
}
