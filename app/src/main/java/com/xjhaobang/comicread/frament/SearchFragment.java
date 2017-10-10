package com.xjhaobang.comicread.frament;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.widget.ImageButton;

import com.xjhaobang.comicread.R;
import com.xjhaobang.comicread.adapter.CategoryComicRvAdapter;
import com.xjhaobang.comicread.base.BaseFragment;
import com.xjhaobang.comicread.been.ComicBeen;
import com.xjhaobang.comicread.constract.GetSearchComicConstract;
import com.xjhaobang.comicread.listener.OnClickRecyclerViewListener;
import com.xjhaobang.comicread.model.Model;
import com.xjhaobang.comicread.presenter.GetSearchComicPresenterImpl;
import com.xjhaobang.comicread.ui.ComicItemActivity;
import com.xjhaobang.comicread.utils.ProgressDialogUtil;
import com.xjhaobang.comicread.view.EditTextWithDel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by PC on 2017/9/27.
 */

public class SearchFragment extends BaseFragment implements GetSearchComicConstract.View {
    @BindView(R.id.ib_search)
    ImageButton mIbSearch;
    @BindView(R.id.et_search)
    EditTextWithDel mEtSearch;
    @BindView(R.id.rv_search)
    RecyclerView mRvSearch;

    private List<ComicBeen> mList;
    private CategoryComicRvAdapter mAdapter;
    private GetSearchComicConstract.Presenter mPresenter;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initData(Bundle bundle) {
        Model model = new Model();
        model.test();
        mList = new ArrayList<>();
        mAdapter = new CategoryComicRvAdapter();
        mAdapter.updateData(mList);
        mPresenter = new GetSearchComicPresenterImpl(this);
    }

    @Override
    protected void initView() {
        mRvSearch.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        mRvSearch.setItemAnimator(new DefaultItemAnimator());
        mRvSearch.setAdapter(mAdapter);
    }

    @Override
    protected void setListener() {
        mAdapter.setOnRecyclerViewListener(new OnClickRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mBaseActivity, ComicItemActivity.class);
                intent.putExtra("comicItemUrl",mList.get(position).getUrl());
                intent.putExtra("comicItemTitle",mList.get(position).getTitle());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });
    }

    @OnClick(R.id.ib_search)
    public void onViewClicked() {
        if (TextUtils.isEmpty(mEtSearch.getText().toString())){
            showToast("请输入您要搜索的关键词");
        }else {
            ProgressDialogUtil.showDefaultDialog(mBaseActivity);
            mPresenter.getSearchComic(mEtSearch.getText().toString());
        }
    }

    @Override
    public void getSearchComicSuccess(List<ComicBeen> list) {
        hideSoftInput();
        mList = list;
        mAdapter.updateData(mList);
        ProgressDialogUtil.dismiss();
    }

    @Override
    public void getError(String msg) {
        ProgressDialogUtil.dismiss();
        hideSoftInput();
        showToast(msg);
    }


}
