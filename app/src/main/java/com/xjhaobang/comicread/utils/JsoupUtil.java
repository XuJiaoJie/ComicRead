package com.xjhaobang.comicread.utils;

import com.xjhaobang.comicread.been.ComicBeen;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 2017/9/28.
 */

public class JsoupUtil {
    private static final String TAG = "JsoupUtil";
    private static JsoupUtil sJsoupUtil;

    public static JsoupUtil getInstance() {
        if (sJsoupUtil == null) {
            synchronized (JsoupUtil.class) {
                if (sJsoupUtil == null) {
                    sJsoupUtil = new JsoupUtil();
                }
            }
        }
        return sJsoupUtil;
    }

    /**
     * 主界面轮播效果漫画数据
     */
    public List<ComicBeen> getPollingData(String s) {
        List<ComicBeen> list = new ArrayList<>();
        Document doc = Jsoup.parse(s);
        Element e = doc.select("ul.banner-slider-list").first();
        Elements es = e.getElementsByTag("li");
        for (Element ee : es) {
            ComicBeen been = new ComicBeen();
            Element ee2 = ee.getElementsByTag("a").first();
            been.setUrl(ee2.attr("href"));
            been.setTitle(ee2.attr("title"));
            Element ee3 = ee2.getElementsByTag("img").first();
            been.setPicUrl(ee3.attr("src"));
            list.add(been);
        }
        return list;
    }

    /**
     * 最新更新漫画数据
     */
    public List<ComicBeen> getUpdateData(String s){
        List<ComicBeen> list = new ArrayList<>();
        Document doc = Jsoup.parse(s);
        Element e = doc.select("ul.japan-new-list").first();
        Elements es = e.select("div.japan-new-mod");
        for (Element ee : es){
            ComicBeen been = new ComicBeen();
            Element ee2 = ee.getElementsByTag("a").first();
            been.setUrl(ee2.attr("href"));
            been.setTitle(ee2.attr("title"));
            Element ee3 = ee2.getElementsByTag("img").first();
            been.setPicUrl(ee3.attr("src"));
            Element ee4 = ee.select("div.new-text").first();
            Elements ees =ee4.getElementsByTag("a");
            been.setUpdate(ees.get(1).text());
            list.add(been);
        }
        return list;
    }

    /**
     * 获取热门漫画数据
     */
    public List<ComicBeen> getHotData(String s){
        List<ComicBeen> list = new ArrayList<>();
        Document doc = Jsoup.parse(s);
        Elements es = doc.select("li.comic-content-item");
        for (Element ee : es ){
            ComicBeen been = new ComicBeen();
            Element ee1 = ee.select("a.Japan-comic-title").first();
            been.setUrl(ee1.attr("href"));
            Element ee2 = ee1.getElementsByTag("img").first();
            String ss = ee2.attr("src");
            if (ss.endsWith("gif")){
                ss = ee2.attr("data-original");
            }
            been.setPicUrl(ss);
            Element ee3 = ee.select("a.japan-comic-a").first();
            been.setTitle(ee3.text());
            Element ee4 = ee.select("a.japan-comic-new").first();
            been.setUpdate(ee4.text());
            list.add(been);
        }
        return list;
    }

    /**
     * 获取分类漫画数据
     */
    public List<ComicBeen> getCategoryData(String s){
        List<ComicBeen> list = new ArrayList<>();
        Document document = Jsoup.parse(s);
        Element element = document.select("div.ret-search-result").first();
        Elements elements = element.getElementsByTag("li");
        for (Element e : elements){
            ComicBeen been = new ComicBeen();
            Element ee0 = e.select("div.ret-works-cover").first();
            Element ee = ee0.getElementsByTag("a").first();
            been.setTitle(ee.attr("title"));
            been.setUrl(ee.attr("href"));
            Element ee1 = ee.getElementsByTag("img").first();
            been.setPicUrl(ee1.attr("data-original"));
            Element ee2 = e.select("span.mod-cover-list-text").first();
            been.setUpdate(ee2.text());
            Element ee3 = e.select("p.ret-works-decs").first();
            been.setMark(ee3.text());
            list.add(been);
        }
        return list;
    }

    /**
     * 搜索漫画数据
     */
    public List<ComicBeen> getSearchData(String s){
        List<ComicBeen> list = new ArrayList<>();
        Document doc = Jsoup.parse(s);
        Element element = doc.select("ul.mod_book_list").select("ul.mod_all_works_list").select("ul.mod_of").first();
        Elements elements = element.getElementsByTag("li");
        for (Element e : elements){
            ComicBeen been = new ComicBeen();
            Element ee = e.getElementsByTag("a").first();
            been.setUrl(ee.attr("href"));
            been.setTitle(ee.attr("title"));
            Element ee1 = ee.getElementsByTag("img").first();
            been.setPicUrl(ee1.attr("data-original"));
            Element ee2 = e.select("h3.mod_book_update").select("h3.fw").first();
            been.setUpdate(ee2.text());
            list.add(been);
        }
        return list;
    }

}
