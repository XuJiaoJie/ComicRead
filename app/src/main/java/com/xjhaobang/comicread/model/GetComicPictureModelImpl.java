package com.xjhaobang.comicread.model;

import com.xjhaobang.comicread.constants.Constant;
import com.xjhaobang.comicread.constract.GetComicPictureConstract;
import com.xjhaobang.comicread.utils.JsoupUtil;
import com.xjhaobang.comicread.utils.OkHttpResultCallback;
import com.xjhaobang.comicread.utils.OkHttpUtil;

import java.io.UnsupportedEncodingException;

import okhttp3.Call;

/**
 * Created by PC on 2017/10/10.
 */

public class GetComicPictureModelImpl implements GetComicPictureConstract.Model {
    private GetComicPictureConstract.Presenter mPresenter;

    public GetComicPictureModelImpl(GetComicPictureConstract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getScriptData(String url) {
        OkHttpUtil.getInstance().getAsync(Constant.GET_COMIC_ITEM_HEAD + url, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                mPresenter.getError(e.getMessage());
            }

            @Override
            public void onResponse(byte[] bytes) {
                try {
                    String s = new String(bytes, "utf-8");
                    String data = JsoupUtil.getInstance().getScriptData(s);
                    mPresenter.getScriptDataSuccess(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
