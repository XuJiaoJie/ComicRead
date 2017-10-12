package com.xjhaobang.comicread.ui;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xjhaobang.comicread.R;
import com.xjhaobang.comicread.base.BaseActivity;
import com.xjhaobang.comicread.utils.ProgressDialogUtil;

import butterknife.BindView;

/**
 * Created by PC on 2017/10/12.
 */

public class ComicWebViewActivity extends BaseActivity {
    @BindView(R.id.web_view)
    WebView mWebView;

    private String mUrl;
    private String mTitle;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_comic_webview;
    }

    @Override
    protected void initData() {
        mUrl = getIntent().getStringExtra("comicItemUrl");
        mTitle = getIntent().getStringExtra("comicItemTitle");
    }

    @Override
    protected void initView() {
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    }

    @Override
    protected void initListener() {
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient(){
            private String startUrl;
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(startUrl!=null && startUrl.equals(url)) {
                    view.loadUrl(url);
                    return true;
                } else {
                    return super.shouldOverrideUrlLoading(view, url);
                }

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                if (!ProgressDialogUtil.isShow()){
                    ProgressDialogUtil.showDefaultDialog(ComicWebViewActivity.this);
                }
                startUrl = url;
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (ProgressDialogUtil.isShow()){
                    ProgressDialogUtil.dismiss();
                }
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
        mWebView.loadUrl(mUrl);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
