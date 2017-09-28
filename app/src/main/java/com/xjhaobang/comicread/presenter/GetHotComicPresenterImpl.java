package com.xjhaobang.comicread.presenter;

import com.xjhaobang.comicread.been.ComicBeen;
import com.xjhaobang.comicread.constract.GetHotComicContract;
import com.xjhaobang.comicread.model.GetHotComicModelImpl;

import java.util.List;

/**
 * Created by PC on 2017/9/28.
 */

public class GetHotComicPresenterImpl implements GetHotComicContract.Presenter {
    private GetHotComicContract.Model mModel;
    private GetHotComicContract.View mView;


    public GetHotComicPresenterImpl(GetHotComicContract.View view) {
        mView = view;
        mModel = new GetHotComicModelImpl(this);
    }

    @Override
    public void getHotComic() {
        mModel.getHotComic();
    }


    @Override
    public void getHotComicSuccess(List<ComicBeen> list) {
        mView.getHotComicSuccess(list);
    }

    @Override
    public void getError(String msg) {
        mView.getError(msg);
    }
}
