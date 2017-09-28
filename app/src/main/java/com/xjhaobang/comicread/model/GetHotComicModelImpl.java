package com.xjhaobang.comicread.model;

import com.xjhaobang.comicread.been.ComicBeen;
import com.xjhaobang.comicread.constants.Constant;
import com.xjhaobang.comicread.constract.GetHotComicContract;
import com.xjhaobang.comicread.utils.JsoupUtil;
import com.xjhaobang.comicread.utils.OkHttpResultCallback;
import com.xjhaobang.comicread.utils.OkHttpUtil;

import java.io.UnsupportedEncodingException;
import java.util.List;

import okhttp3.Call;

/**
 * Created by PC on 2017/9/28.
 */

public class GetHotComicModelImpl implements GetHotComicContract.Model {
    private GetHotComicContract.Presenter mPresenter;

    public GetHotComicModelImpl(GetHotComicContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getHotComic() {
        OkHttpUtil.getInstance().getAsync(Constant.GET_Mian_Data, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                mPresenter.getError(e.getMessage());
            }

            @Override
            public void onResponse(byte[] bytes) {
                try {
                    String s = new String(bytes,"utf-8");
                    List<ComicBeen> list = JsoupUtil.getInstance().getHotData(s);
                    if (list != null){
                        mPresenter.getHotComicSuccess(list);
                    }else {
                        mPresenter.getError("获取热门漫画数据出错啦....");
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
