package com.xjhaobang.comicread.frament;

import android.os.Bundle;
import android.widget.TextView;

import com.xjhaobang.comicread.R;
import com.xjhaobang.comicread.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by PC on 2017/9/29.
 */

public class CategoryContentFragment extends BaseFragment {
    @BindView(R.id.tv)
    TextView mTv;

    private int mIndex;

    public static CategoryContentFragment newInstance(int index) {
        CategoryContentFragment fragment = new CategoryContentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("fragmentID", index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_category_content;
    }

    @Override
    protected void initData(Bundle bundle) {
        mIndex = bundle.getInt("fragmentID");
        mTv.setText(mIndex+"");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }

}
