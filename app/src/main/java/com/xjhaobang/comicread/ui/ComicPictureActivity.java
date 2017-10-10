package com.xjhaobang.comicread.ui;

import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xjhaobang.comicread.R;
import com.xjhaobang.comicread.base.BaseToolbarActivity;
import com.xjhaobang.comicread.been.Episode;
import com.xjhaobang.comicread.constract.GetComicPictureConstract;
import com.xjhaobang.comicread.presenter.GetComicPicturePresenterImpl;
import com.xjhaobang.comicread.utils.ProgressDialogUtil;

import java.util.List;

/**
 * Created by PC on 2017/10/10.
 */

public class ComicPictureActivity extends BaseToolbarActivity implements GetComicPictureConstract.View{
    private static final String TAG = "ComicPictureActivity";
    private WebView mWebView;
    private Episode mEpisode;
    private GetComicPicturePresenterImpl mPresenter;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_comic_picture;
    }

    @Override
    protected void initData() {
        mEpisode = (Episode) getIntent().getSerializableExtra("Episode");
        mPresenter = new GetComicPicturePresenterImpl(this);
    }

    @Override
    protected void initView() {
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setTitle(mEpisode.getTitle());
        mWebView = new WebView(this);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(mPresenter,"jsPicture");
        ProgressDialogUtil.showDefaultDialog(this);
        mPresenter.getScriptData(mEpisode.getUrl());
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void getScriptDataSuccess(final String data) {
        mWebView.loadUrl("file:///android_asset/jsComicPicture.html");
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                mWebView.loadUrl("javascript:aaa('"+ data + " ')");
                super.onPageFinished(view, url);
            }
        });
    }

    @Override
    public void getComicPictureSuccess(List<String> urls) {
        ProgressDialogUtil.dismiss();
        Log.e(TAG, "getComicPictureSuccess: " + urls.toString() );
    }

    @Override
    public void getError(String msg) {
        ProgressDialogUtil.dismiss();
        showToast(msg);
    }
}
