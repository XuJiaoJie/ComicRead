package com.xjhaobang.comicread.ui;

import android.net.Uri;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xjhaobang.comicread.R;
import com.xjhaobang.comicread.base.BaseActivity;
import com.xjhaobang.comicread.base.BaseToolbarActivity;
import com.xjhaobang.comicread.been.ComicItem;
import com.xjhaobang.comicread.constract.GetComicItemConstract;
import com.xjhaobang.comicread.presenter.GetComicItemPresenterImpl;
import com.xjhaobang.comicread.utils.ProgressDialogUtil;

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

    private String mUrl;
    private String mTitle;
    private GetComicItemConstract.Presenter mPresenter;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_comic_item;
    }

    @Override
    protected void initData() {
        mUrl = getIntent().getStringExtra("comicItemUrl");
        mTitle = getIntent().getStringExtra("comicItemTitle");
        mPresenter = new GetComicItemPresenterImpl(this);
    }

    @Override
    protected void initView() {
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setTitle(mTitle);
        ProgressDialogUtil.showDefaultDialog(this);
        mPresenter.getComicItem(mUrl);
    }

    @Override
    protected void initListener() {

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
    }

    @Override
    public void getError(String msg) {
        ProgressDialogUtil.dismiss();
        showToast(msg);
    }

}
