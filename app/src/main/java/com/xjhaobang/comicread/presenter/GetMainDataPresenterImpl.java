package com.xjhaobang.comicread.presenter;

import com.xjhaobang.comicread.been.ComicBeen;
import com.xjhaobang.comicread.constract.GetMainDataContract;
import com.xjhaobang.comicread.model.GetMainDataModelImpl;

import java.util.List;

/**
 * Created by PC on 2017/9/28.
 */

public class GetMainDataPresenterImpl implements GetMainDataContract.Presenter {
    private GetMainDataContract.Model mModel;
    private GetMainDataContract.View mView;

    public GetMainDataPresenterImpl(GetMainDataContract.View view) {
        mView = view;
        mModel = new GetMainDataModelImpl(this);
    }

    @Override
    public void getMainData() {
        mModel.getMainData();
    }

    @Override
    public void getMainDataSuccess(List<ComicBeen> pollingList, List<ComicBeen> updateList) {
        mView.getMainDataSuccess(pollingList,updateList);
    }

    @Override
    public void getError(String msg) {
        mView.getError(msg);
    }
}
