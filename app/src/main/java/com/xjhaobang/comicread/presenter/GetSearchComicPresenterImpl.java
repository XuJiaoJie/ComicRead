package com.xjhaobang.comicread.presenter;

import com.xjhaobang.comicread.been.ComicBeen;
import com.xjhaobang.comicread.constract.GetSearchComicConstract;
import com.xjhaobang.comicread.model.GetSearchComicModelImpl;

import java.util.List;

/**
 * Created by PC on 2017/9/30.
 */

public class GetSearchComicPresenterImpl implements GetSearchComicConstract.Presenter {
    private GetSearchComicConstract.Model mModel;
    private GetSearchComicConstract.View mView;

    public GetSearchComicPresenterImpl(GetSearchComicConstract.View view) {
        mView = view;
        mModel = new GetSearchComicModelImpl(this);
    }

    @Override
    public void getSearchComic(String key) {
        mModel.getSearchComic(key);
    }


    @Override
    public void getSearchComicSuccess(List<ComicBeen> list) {
        mView.getSearchComicSuccess(list);
    }

    @Override
    public void getError(String msg) {
        mView.getError(msg);
    }
}
