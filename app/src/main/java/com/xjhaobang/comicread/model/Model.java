package com.xjhaobang.comicread.model;

import android.util.Log;

import com.xjhaobang.comicread.utils.OkHttpResultCallback;
import com.xjhaobang.comicread.utils.OkHttpUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;

import okhttp3.Call;

import static android.content.ContentValues.TAG;

/**
 * Created by PC on 2017/9/26.
 */

public class Model {

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

    public void test(){
        OkHttpUtil.getInstance().getAsync("http://ac.qq.com/Jump", new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Log.e(TAG, "onError: "  );
            }

            @Override
            public void onResponse(byte[] bytes) {
                try {
                    String s = new String(bytes,"utf-8");
                    Document doc = Jsoup.parse(s);
//                    //主界面轮播
//                    Element e = doc.select("ul.banner-slider-list").first();
//                    Elements es = e.getElementsByTag("li");
//                    for (Element ee : es){
//                        Element ee2 = ee.getElementsByTag("a").first();
//                        Log.e(TAG, "onResponse: " + ee2.attr("href"));
//                        Log.e(TAG, "onResponse: " + ee2.attr("title"));
//                        Element ee3 = ee2.getElementsByTag("img").first();
//                        Log.e(TAG, "onResponse: " + ee3.attr("src"));
//                    }
                    Element e = doc.select("ul.japan-new-list").first();
                    Elements es = e.select("div.japan-new-mod");
                    for (Element ee : es){
                        Element ee2 = ee.getElementsByTag("a").first();
                        Log.e(TAG, "onResponse: " + ee2.attr("href"));
                        Log.e(TAG, "onResponse: " + ee2.attr("title"));
                        Element ee3 = ee2.getElementsByTag("img").first();
                        Log.e(TAG, "onResponse: " + ee3.attr("src"));
                        Element ee4 = ee.select("div.new-text").first();
                        Elements ees =ee4.getElementsByTag("a");
                        Log.e(TAG, "onResponse: " + ees.get(1).text());
                    }

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
