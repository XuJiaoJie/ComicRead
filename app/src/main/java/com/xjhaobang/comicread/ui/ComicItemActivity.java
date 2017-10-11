package com.xjhaobang.comicread.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xjhaobang.comicread.R;
import com.xjhaobang.comicread.adapter.EpisodeRvAdapter;
import com.xjhaobang.comicread.base.BaseToolbarActivity;
import com.xjhaobang.comicread.been.ComicItem;
import com.xjhaobang.comicread.been.Episode;
import com.xjhaobang.comicread.constract.GetComicItemConstract;
import com.xjhaobang.comicread.listener.OnClickRecyclerViewListener;
import com.xjhaobang.comicread.presenter.GetComicItemPresenterImpl;
import com.xjhaobang.comicread.utils.ProgressDialogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by PC on 2017/9/30.
 */

public class ComicItemActivity extends BaseToolbarActivity implements GetComicItemConstract.View {
    @BindView(R.id.sdv_pic)
    SimpleDraweeView mSdvPic;
    @BindView(R.id.tv_status)
    TextView mTvStatus;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_score)
    TextView mTvScore;
    @BindView(R.id.tv_author)
    TextView mTvAuthor;
    @BindView(R.id.tv_summary)
    TextView mTvSummary;
    @BindView(R.id.rv_episode)
    RecyclerView mRvEpisode;

    private String mUrl;
    private String mTitle;
    private GetComicItemConstract.Presenter mPresenter;
    private EpisodeRvAdapter mAdapter;
    private List<Episode> mList;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_comic_item;
    }

    @Override
    protected void initData() {
        mUrl = getIntent().getStringExtra("comicItemUrl");
        mTitle = getIntent().getStringExtra("comicItemTitle");
        mPresenter = new GetComicItemPresenterImpl(this);
        mList = new ArrayList<>();
        mAdapter = new EpisodeRvAdapter();
        mAdapter.updateData(mList);
    }

    @Override
    protected void initView() {
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setTitle(mTitle);
        mRvEpisode.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        mRvEpisode.setItemAnimator(new DefaultItemAnimator());
        mRvEpisode.setAdapter(mAdapter);
        ProgressDialogUtil.showDefaultDialog(this);
        mPresenter.getComicItem(mUrl);
    }

    @Override
    protected void initListener() {
        mAdapter.setOnRecyclerViewListener(new OnClickRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(ComicItemActivity.this,ComicPictureActivity.class);
                intent.putExtra("Episode",mList.get(position));
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });
    }

    @Override
    public void getComicItemSuccess(ComicItem comicItem) {
        ProgressDialogUtil.dismiss();
        mSdvPic.setImageURI(Uri.parse(comicItem.getPicUrl()));
        mTvStatus.setText(comicItem.getStatus());
        mTvTitle.setText(comicItem.getTitle());
        mTvScore.setText(comicItem.getScore());
        mTvAuthor.setText(comicItem.getAuthor());
        mTvSummary.setText(comicItem.getSummary());
        mList = comicItem.getEpisodeList();
        mAdapter.updateData(mList);
    }

    @Override
    public void getError(String msg) {
        ProgressDialogUtil.dismiss();
        showToast(msg);
    }


}
