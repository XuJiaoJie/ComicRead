package com.xjhaobang.comicread.presenter;

import com.xjhaobang.comicread.been.ComicBeen;
import com.xjhaobang.comicread.constract.GetCategoryComicConstract;
import com.xjhaobang.comicread.model.GetCategoryComicModelImpl;

import java.util.List;

/**
 * Created by PC on 2017/9/29.
 */

public class GetCategoryComicPresenterImpl implements GetCategoryComicConstract.Presenter {
    private GetCategoryComicConstract.View mView;
    private GetCategoryComicConstract.Model mModel;

    public GetCategoryComicPresenterImpl(GetCategoryComicConstract.View view) {
        mView = view;
        mModel = new GetCategoryComicModelImpl(this);
    }

    @Override
    public void getCategoryComic(int type, int pageNum) {
        mModel.getCategoryComic(type,pageNum);
    }


    @Override
    public void getCategoryComicSuccess(List<ComicBeen> list) {
        mView.getCategoryComicSuccess(list);
    }

    @Override
    public void getError(String msg) {
        mView.getError(msg);
    }
}
