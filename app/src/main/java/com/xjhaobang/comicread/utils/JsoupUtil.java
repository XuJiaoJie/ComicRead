package com.xjhaobang.comicread.utils;

import com.xjhaobang.comicread.been.ComicBeen;
import com.xjhaobang.comicread.been.ComicItem;
import com.xjhaobang.comicread.been.Episode;

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
    public List<ComicBeen> getUpdateData(String s) {
        List<ComicBeen> list = new ArrayList<>();
        Document doc = Jsoup.parse(s);
        Element e = doc.select("ul.japan-new-list").first();
        Elements es = e.select("div.japan-new-mod");
        for (Element ee : es) {
            ComicBeen been = new ComicBeen();
            Element ee2 = ee.getElementsByTag("a").first();
            been.setUrl(ee2.attr("href"));
            been.setTitle(ee2.attr("title"));
            Element ee3 = ee2.getElementsByTag("img").first();
            been.setPicUrl(ee3.attr("src"));
            Element ee4 = ee.select("div.new-text").first();
            Elements ees = ee4.getElementsByTag("a");
            been.setUpdate(ees.get(1).text());
            list.add(been);
        }
        return list;
    }

    /**
     * 获取热门漫画数据
     */
    public List<ComicBeen> getHotData(String s) {
        List<ComicBeen> list = new ArrayList<>();
        Document doc = Jsoup.parse(s);
        Elements es = doc.select("li.comic-content-item");
        for (Element ee : es) {
            ComicBeen been = new ComicBeen();
            Element ee1 = ee.select("a.Japan-comic-title").first();
            been.setUrl(ee1.attr("href"));
            Element ee2 = ee1.getElementsByTag("img").first();
            String ss = ee2.attr("src");
            if (ss.endsWith("gif")) {
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
    public List<ComicBeen> getCategoryData(String s) {
        List<ComicBeen> list = new ArrayList<>();
        Document document = Jsoup.parse(s);
        Element element = document.select("div.ret-search-result").first();
        Elements elements = element.getElementsByTag("li");
        for (Element e : elements) {
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
    public List<ComicBeen> getSearchData(String s) {
        List<ComicBeen> list = new ArrayList<>();
        Document doc = Jsoup.parse(s);
        Element element = doc.select("ul.mod_book_list").select("ul.mod_all_works_list").select("ul.mod_of").first();
        Elements elements = element.getElementsByTag("li");
        for (Element e : elements) {
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

    /**
     * 解析漫画详情数据
     */
    public ComicItem getComicItem(String s) {
        ComicItem comicItem = new ComicItem();
        Document document = Jsoup.parse(s);
        Element element = document.select("div.works-intro").first();
        Element element1 = element.select("div.works-cover").first();
        Element element11 = element1.getElementsByTag("a").first();
        comicItem.setTitle(element11.attr("title"));
        Element element111 = element11.getElementsByTag("img").first();
        comicItem.setPicUrl(element111.attr("src"));
        Element element12 = element1.select("label.works-intro-status").first();
        comicItem.setStatus(element12.text());
        Element element2 = element.select("strong.ui-text-orange").first();
        if (element2 != null) {
            comicItem.setScore(element2.text());
        } else {
            comicItem.setScore("暂未评分");
        }
        Element element3 = element.select("span.first").first();
        comicItem.setAuthor(element3.text());
        Element element4 = element.select("p.works-intro-short").select("p.ui-text-gray9").first();
        comicItem.setSummary(element4.text());
        Element e0 = document.select("ol.chapter-page-all").select("ol.works-chapter-list").first();
        Elements es = e0.select("span.works-chapter-item");
        List<Episode> list = new ArrayList<>();
        for (Element e : es) {
            Episode ep = new Episode();
            Element ee = e.getElementsByTag("a").first();
            ep.setUrl(ee.attr("href"));
            ep.setTitle(ee.text());
            list.add(ep);
        }
        comicItem.setEpisodeList(list);
        return comicItem;
    }

//    /**
//     * 解析漫画图片
//     */
//    public List<String> getComicPic(String s){
//        Document document = Jsoup.parse(s);
//        Log.e(TAG, "getComicPic: 1" );
//        Element element = document.select("ul.comic-contain").first();
//        Element e0 = element.getElementsByTag("script").first();
//        String s0 = e0.html();
//        Elements elements = element.getElementsByTag("li");
//        for (Element e : elements){
//            Element ee = e.getElementsByTag("img").first();
//        }
//        return null;
//    }

    /**
     * 抓取script标签的DATA字符串
     */
    public String getScriptData(String s) {
        Document document = Jsoup.parse(s);
        Elements elements = document.select("script");
        String msg = elements.get(elements.size() - 4).html();
        String data = msg.substring(msg.indexOf("\'") + 1, msg.lastIndexOf("\'"));
        return data;
    }


}
