package com.xjhaobang.comicread.been;

import java.util.List;

/**
 * Created by PC on 2017/9/30.
 */

public class ComicItem {
    private String picUrl;
    private String title;
    private String status;
    private String score;
    private String author;
    private String summary;
    private List<Episode> mEpisodeList;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<Episode> getEpisodeList() {
        return mEpisodeList;
    }

    public void setEpisodeList(List<Episode> episodeList) {
        mEpisodeList = episodeList;
    }
}
