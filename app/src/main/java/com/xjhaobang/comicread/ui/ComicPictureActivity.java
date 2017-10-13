package com.xjhaobang.comicread.ui;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xjhaobang.comicread.R;
import com.xjhaobang.comicread.adapter.ComicPictureRvAdapter;
import com.xjhaobang.comicread.base.BaseToolbarActivity;
import com.xjhaobang.comicread.been.Episode;
import com.xjhaobang.comicread.constract.GetComicPictureConstract;
import com.xjhaobang.comicread.listener.OnClickHolderInside;
import com.xjhaobang.comicread.presenter.GetComicPicturePresenterImpl;
import com.xjhaobang.comicread.utils.ProgressDialogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by PC on 2017/10/10.
 */

public class ComicPictureActivity extends BaseToolbarActivity implements GetComicPictureConstract.View {
    private static final String TAG = "ComicPictureActivity";
    @BindView(R.id.rv_picture)
    RecyclerView mRvPicture;
    private WebView mWebView;
    private Episode mEpisode;
    private GetComicPicturePresenterImpl mPresenter;
    private List<String> mUrlList;
    private ComicPictureRvAdapter mAdapter;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_comic_picture;
    }

    @Override
    protected void initData() {
        mEpisode = (Episode) getIntent().getSerializableExtra("Episode");
        mPresenter = new GetComicPicturePresenterImpl(this);
        mUrlList = new ArrayList<>();
        mAdapter = new ComicPictureRvAdapter();
        mAdapter.updateData(mUrlList);
    }

    @Override
    protected void initView() {
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setTitle(mEpisode.getTitle());
        mRvPicture.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRvPicture.setItemAnimator(new DefaultItemAnimator());
        mRvPicture.setAdapter(mAdapter);
        mWebView = new WebView(this);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(mPresenter, "jsPicture");
        ProgressDialogUtil.showDefaultDialog(this);
        mPresenter.getScriptData(mEpisode.getUrl());
    }

    @Override
    protected void initListener() {
        mAdapter.setOnClickHolderInside(new OnClickHolderInside() {
            @Override
            public void onClicked(int position) {
                Intent intent = DetailsPhotoActivity.newIntent(ComicPictureActivity.this,position,(ArrayList<String>) mUrlList);
                startActivity(intent);
            }
        });
    }

    @Override
    public void getScriptDataSuccess(final String data) {
        mWebView.loadUrl("file:///android_asset/jsComicPicture.html");
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                mWebView.loadUrl("javascript:aaa('" + data + " ')");
                Log.e(TAG, "onPageFinished: " + data );
                super.onPageFinished(view, url);
            }
        });
    }

    @Override
    public void getComicPictureSuccess(final List<String> urls) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ProgressDialogUtil.dismiss();
                mUrlList = urls;
                mAdapter.updateData(mUrlList);
            }
        });

    }

    @Override
    public void getError(String msg) {
        ProgressDialogUtil.dismiss();
        showToast(msg);
        if (msg.equals("该漫画为付费漫画，观看不了...")){
            finish();
        }
    }

}
