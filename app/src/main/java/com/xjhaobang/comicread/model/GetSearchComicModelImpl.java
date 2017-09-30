package com.xjhaobang.comicread.model;

import com.xjhaobang.comicread.been.ComicBeen;
import com.xjhaobang.comicread.constants.Constant;
import com.xjhaobang.comicread.constract.GetSearchComicConstract;
import com.xjhaobang.comicread.utils.JsoupUtil;
import com.xjhaobang.comicread.utils.OkHttpResultCallback;
import com.xjhaobang.comicread.utils.OkHttpUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import okhttp3.Call;

/**
 * Created by PC on 2017/9/30.
 */

public class GetSearchComicModelImpl implements GetSearchComicConstract.Model {
    private GetSearchComicConstract.Presenter mPresenter;

    public GetSearchComicModelImpl(GetSearchComicConstract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSearchComic(String key) {
        String keyUrlEncode = null;
        try {
            keyUrlEncode = URLEncoder.encode(key,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        OkHttpUtil.getInstance().getAsync(Constant.GET_SEARCH_URL + keyUrlEncode, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                mPresenter.getError(e.getMessage());
            }

            @Override
            public void onResponse(byte[] bytes) {
                try {
                    String s = new String(bytes,"utf-8");
                    List<ComicBeen> list = JsoupUtil.getInstance().getSearchData(s);
                    mPresenter.getSearchComicSuccess(list);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
