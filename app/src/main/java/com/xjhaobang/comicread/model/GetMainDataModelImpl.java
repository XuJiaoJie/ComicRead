package com.xjhaobang.comicread.model;

import com.xjhaobang.comicread.been.ComicBeen;
import com.xjhaobang.comicread.constants.Constant;
import com.xjhaobang.comicread.constract.GetMainDataContract;
import com.xjhaobang.comicread.utils.JsoupUtil;
import com.xjhaobang.comicread.utils.OkHttpResultCallback;
import com.xjhaobang.comicread.utils.OkHttpUtil;

import java.io.UnsupportedEncodingException;
import java.util.List;

import okhttp3.Call;

/**
 * Created by PC on 2017/9/28.
 */

public class GetMainDataModelImpl implements GetMainDataContract.Model {
    private GetMainDataContract.Presenter mPresenter;

    public GetMainDataModelImpl(GetMainDataContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getMainData() {
        OkHttpUtil.getInstance().getAsync(Constant.GET_Mian_Data, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                mPresenter.getError(e.getMessage());
            }

            @Override
            public void onResponse(byte[] bytes) {
                try {
                    String s = new String(bytes,"utf-8");
                    List<ComicBeen> pollingList = JsoupUtil.getInstance().getPollingData(s);
                    List<ComicBeen> updateList = JsoupUtil.getInstance().getUpdateData(s);
                    if (pollingList!=null && updateList!=null){
                        mPresenter.getMainDataSuccess(pollingList,updateList);
                    }else {
                        mPresenter.getError("获取数据出错啦....");
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
