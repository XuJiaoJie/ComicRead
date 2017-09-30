package com.xjhaobang.comicread.model;

import com.xjhaobang.comicread.been.ComicItem;
import com.xjhaobang.comicread.constants.Constant;
import com.xjhaobang.comicread.constract.GetComicItemConstract;
import com.xjhaobang.comicread.utils.JsoupUtil;
import com.xjhaobang.comicread.utils.OkHttpResultCallback;
import com.xjhaobang.comicread.utils.OkHttpUtil;

import java.io.UnsupportedEncodingException;

import okhttp3.Call;

/**
 * Created by PC on 2017/9/30.
 */

public class GetComicItemModelImpl implements GetComicItemConstract.Model {
    private GetComicItemConstract.Presenter mPresenter;

    public GetComicItemModelImpl(GetComicItemConstract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getComicItem(String url) {
        OkHttpUtil.getInstance().getAsync(Constant.GET_COMIC_ITEM_HEAD + url, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                mPresenter.getComicItemError(e.getMessage());
            }

            @Override
            public void onResponse(byte[] bytes) {
                try {
                    String s = new String(bytes,"utf-8");
                    ComicItem comicItem = JsoupUtil.getInstance().getComicItem(s);
                    mPresenter.getComicItemSuccess(comicItem);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
