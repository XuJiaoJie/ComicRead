package com.xjhaobang.comicread.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xjhaobang.comicread.R;
import com.xjhaobang.comicread.adapter.EpisodeRvAdapter;
import com.xjhaobang.comicread.base.BaseToolbarActivity;
import com.xjhaobang.comicread.been.ComicItem;
import com.xjhaobang.comicread.been.Episode;
import com.xjhaobang.comicread.constract.GetComicItemConstract;
import com.xjhaobang.comicread.listener.OnClickRecyclerViewListener;
import com.xjhaobang.comicread.listener.ScrollBottomListener;
import com.xjhaobang.comicread.presenter.GetComicItemPresenterImpl;
import com.xjhaobang.comicread.utils.ProgressDialogUtil;
import com.xjhaobang.comicread.view.NoScrollLayoutManager;
import com.xjhaobang.comicread.view.ScrollBottomScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by PC on 2017/9/30.
 */

public class ComicItemActivity extends BaseToolbarActivity implements GetComicItemConstract.View, ScrollBottomListener {
    private static final String TAG = "ComicItemActivity";
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
    @BindView(R.id.scrollView)
    ScrollBottomScrollView mScrollView;
    @BindView(R.id.ll_loading)
    LinearLayout mLlLoading;
    @BindView(R.id.ll_no_more)
    LinearLayout mLlNoMore;

    private String mUrl;
    private String mTitle;
    private GetComicItemConstract.Presenter mPresenter;
    private EpisodeRvAdapter mAdapter;
    private List<Episode> mList, mTempList;
    private int page = 0;


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
        mTempList = new ArrayList<>();
        mAdapter = new EpisodeRvAdapter();
        mAdapter.updateData(mList);
    }

    @Override
    protected void initView() {
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setTitle(mTitle);
//        mRvEpisode.setNestedScrollingEnabled(false); // 解决滑动不顺畅
//        mRvEpisode.setHasFixedSize(false); // 解决滑动不顺畅
//        mRvEpisode.setFocusable(false); // 防止RecyclerView加载数据的时候抢占焦点，把顶部view遮挡了
        mRvEpisode.setLayoutManager(new NoScrollLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRvEpisode.setItemAnimator(new DefaultItemAnimator());
        mRvEpisode.setAdapter(mAdapter);
        ProgressDialogUtil.showDefaultDialog(this);
        mPresenter.getComicItem(mUrl);
    }

    @Override
    protected void initListener() {
        mScrollView.setScrollBottomListener(this);
        mAdapter.setOnRecyclerViewListener(new OnClickRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(ComicItemActivity.this, ComicPictureActivity.class);
                intent.putExtra("Episode", mList.get(position));
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });
    }

    @Override
    public void scrollBottom() {
        Log.e(TAG, "scrollBottom: " + "滑到底部了");
        if (mTempList.size() > page * 40) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int num;
                            if (mTempList.size() < page*40+40){
                                num = mTempList.size();
                            }else {
                                num = page*40+40;
                            }
                            List<Episode> list = mTempList.subList(page * 40, num);
                            page++;
                            mList.addAll(list);
                            mAdapter.appendData(list);
                            mScrollView.onBottomCompete();
                        }
                    });
                }
            }).start();
        } else {
            mScrollView.loadBottomOver();
            mLlLoading.setVisibility(View.GONE);
            mLlNoMore.setVisibility(View.VISIBLE);
        }
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
        mTempList = comicItem.getEpisodeList();
        if (mTempList.size() <= 40) {
            mList = mTempList;
            mScrollView.loadBottomOver();
            mLlLoading.setVisibility(View.GONE);
            mLlNoMore.setVisibility(View.VISIBLE);
        } else {
            mList.addAll(mTempList.subList(0, 40));
            page++;
        }
        mAdapter.updateData(mList);
    }

    @Override
    public void getError(String msg) {
        ProgressDialogUtil.dismiss();
        showToast(msg);
    }
}
