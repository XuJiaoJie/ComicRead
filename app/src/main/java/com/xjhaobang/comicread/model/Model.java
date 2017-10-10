package com.xjhaobang.comicread.model;

import android.util.Log;

import com.xjhaobang.comicread.utils.JsoupUtil;
import com.xjhaobang.comicread.utils.OkHttpResultCallback;
import com.xjhaobang.comicread.utils.OkHttpUtil;

import java.io.UnsupportedEncodingException;

import okhttp3.Call;

/**
 * Created by PC on 2017/9/26.
 */

public class Model {
    private static final String TAG = "Model";

//    public Model() {
//        OkHttpUtil.getInstance().getAsync("http://ac.qq.com/Comic/all/state/pink/search/time/vip/1/page/1", new OkHttpResultCallback() {
//            @Override
//            public void onError(Call call, Exception e) {
//                Log.e(TAG, "onError: " );
//            }
//
//            @Override
//            public void onResponse(byte[] bytes) {
//                try {
//                    String s = new String(bytes,"utf-8");
//                    Document doc = Jsoup.parse(s);
//                    Elements e = doc.select("div.ret-works-cover");
//                    for (Element e1 : e){
//                        Element e2 = e1.getElementsByTag("a").first();
//                        Log.e(TAG, "run: " + e2.attr("title"));
//                        Log.e(TAG, "run: " + e2.attr("href"));
//                        Element e3 = e2.getElementsByTag("img").first();
//                        Log.e(TAG, "run: " + e3.attr("data-original"));
//                    }
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    public void test() {
//        Map<String,String> map = new HashMap<>();
//        map.put("id","505430");
//        map.put("cid","897");
//        OkHttpUtil.getInstance().postAsync("http://ac.qq.com/ComicView/getNextChapterPicture", new OkHttpResultCallback() {
//            @Override
//            public void onError(Call call, Exception e) {
//                Log.e(TAG, "onError: " + e.getMessage() );
//            }
//
//            @Override
//            public void onResponse(byte[] bytes) {
//                try {
//                    String s = new String(bytes,"utf-8");
//                    Log.e(TAG, "onResponse: " + s);
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//            }
//        },map,null);


        OkHttpUtil.getInstance().getAsync("http://ac.qq.com/ComicView/index/id/501661/cid/700", new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onResponse(byte[] bytes) {
                try {
                    String s = new String(bytes, "utf-8");
                    JsoupUtil.getInstance().getScriptData(s);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });


    }

}
