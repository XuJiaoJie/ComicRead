package com.xjhaobang.comicread.presenter;

import com.xjhaobang.comicread.been.ComicItem;
import com.xjhaobang.comicread.constract.GetComicItemConstract;
import com.xjhaobang.comicread.model.GetComicItemModelImpl;

/**
 * Created by PC on 2017/9/30.
 */

public class GetComicItemPresenterImpl implements GetComicItemConstract.Presenter {
    private GetComicItemConstract.View mView;
    private GetComicItemConstract.Model mModel;

    public GetComicItemPresenterImpl(GetComicItemConstract.View view) {
        mView = view;
        mModel = new GetComicItemModelImpl(this);
    }

    @Override
    public void getComicItem(String url) {
        mModel.getComicItem(url);
    }


    @Override
    public void getComicItemSuccess(ComicItem comicItem) {
        mView.getComicItemSuccess(comicItem);
    }

    @Override
    public void getComicItemError(String msg) {
        mView.getError(msg);
    }
}
