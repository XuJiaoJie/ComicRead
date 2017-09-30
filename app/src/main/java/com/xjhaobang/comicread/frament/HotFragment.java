package com.xjhaobang.comicread.frament;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xjhaobang.comicread.R;
import com.xjhaobang.comicread.adapter.HotComicRvAdapter;
import com.xjhaobang.comicread.base.BaseFragment;
import com.xjhaobang.comicread.been.ComicBeen;
import com.xjhaobang.comicread.constract.GetHotComicContract;
import com.xjhaobang.comicread.listener.OnClickRecyclerViewListener;
import com.xjhaobang.comicread.presenter.GetHotComicPresenterImpl;
import com.xjhaobang.comicread.ui.ComicItemActivity;
import com.xjhaobang.comicread.utils.ProgressDialogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by PC on 2017/9/27.
 */

public class HotFragment extends BaseFragment implements GetHotComicContract.View ,OnClickRecyclerViewListener{
    @BindView(R.id.rv_hot)
    RecyclerView mRvHot;

    private List<ComicBeen> mList;
    private HotComicRvAdapter mRvAdapter;
    private GetHotComicPresenterImpl mPresenter;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initData(Bundle bundle) {
        mList = new ArrayList<>();
        mRvAdapter = new HotComicRvAdapter();
        mPresenter = new GetHotComicPresenterImpl(this);
    }

    @Override
    protected void initView() {
        mRvHot.setLayoutManager(new LinearLayoutManager(mBaseActivity,LinearLayoutManager.VERTICAL,false));
        mRvHot.setItemAnimator(new DefaultItemAnimator());
        mRvAdapter.updateData(mList);
        mRvHot.setAdapter(mRvAdapter);
        ProgressDialogUtil.showDefaultDialog(mBaseActivity);
        mPresenter.getHotComic();
    }

    @Override
    protected void setListener() {
        mRvAdapter.setOnRecyclerViewListener(this);
    }

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

    @Override
    public void getHotComicSuccess(List<ComicBeen> list) {
        ProgressDialogUtil.dismiss();
        mList = list;
        mRvAdapter.updateData(list);
    }

    @Override
    public void getError(String msg) {
        ProgressDialogUtil.dismiss();
        showToast(msg);
    }

}
