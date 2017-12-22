package com.xjhaobang.comicread.presenter;

import android.webkit.JavascriptInterface;

import com.xjhaobang.comicread.constract.GetComicPictureConstract;
import com.xjhaobang.comicread.model.GetComicPictureModelImpl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 2017/10/10.
 */

public class GetComicPicturePresenterImpl implements GetComicPictureConstract.Presenter {
    private GetComicPictureConstract.View mView;
    private GetComicPictureConstract.Model mModel;

    public GetComicPicturePresenterImpl(GetComicPictureConstract.View view) {
        mView = view;
        mModel = new GetComicPictureModelImpl(this);
    }

    @Override
    public void getScriptData(String url) {
        mModel.getScriptData(url);
    }


    @Override
    public void getScriptDataSuccess(String data) {
        mView.getScriptDataSuccess(data);
    }

    @Override
    public void getError(String msg) {
        mView.getError(msg);
    }

    @JavascriptInterface
    public void jsComicJson(String picJson){
        try {
            JSONObject jsonObject = new JSONObject(picJson);
            JSONObject jsonObject1 = jsonObject.getJSONObject("chapter");
            if (jsonObject1.getBoolean("canRead")){
                JSONArray jsonArray = jsonObject.getJSONArray("picture");
                List<String> urlList = new ArrayList<>();
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                    urlList.add(jsonObject2.getString("url"));
                }
                mView.getComicPictureSuccess(urlList);
            }else {
                mView.getError("该漫画为付费漫画，观看不了...");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
