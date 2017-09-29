package com.xjhaobang.comicread.model;

import com.xjhaobang.comicread.been.ComicBeen;
import com.xjhaobang.comicread.constants.Constant;
import com.xjhaobang.comicread.constract.GetCategoryComicConstract;
import com.xjhaobang.comicread.utils.JsoupUtil;
import com.xjhaobang.comicread.utils.OkHttpResultCallback;
import com.xjhaobang.comicread.utils.OkHttpUtil;

import java.io.UnsupportedEncodingException;
import java.util.List;

import okhttp3.Call;

/**
 * Created by PC on 2017/9/29.
 */

public class GetCategoryComicModelImpl implements GetCategoryComicConstract.Model {
    private static final String TAG = "GetCategoryComicModelIm";
    private GetCategoryComicConstract.Presenter mPresenter;

    public GetCategoryComicModelImpl(GetCategoryComicConstract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getCategoryComic(int type, int pageNum) {
        String url;
        if (type == 0) {
            url = Constant.GET_CATEGORY_ALL + pageNum;
        }else if (type == 14){
            url = Constant.GET_CATEGORY_JAPAN + pageNum;
        } else {
            url = Constant.GET_CATEGORY_THEME_HEAD + type + Constant.GET_CATEGORY_THEME_TAIL + pageNum;
        }
        OkHttpUtil.getInstance().getAsync(url, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                mPresenter.getError(e.getMessage());
            }

            @Override
            public void onResponse(byte[] bytes) {
                try {
                    String s = new String(bytes, "utf-8");
                    List<ComicBeen> list = JsoupUtil.getInstance().getCategoryData(s);
                    mPresenter.getCategoryComicSuccess(list);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
