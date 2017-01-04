package com.tianshaokai.crawler.entity;

import java.io.Serializable;

public class TargetInfo implements Serializable {
    private Long id;
    private String url;
    private String title;
    private String pageId;
    private String createTime;
    private String imageCount;
    private String hash;

    private Long homePageId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getImageCount() {
        return imageCount;
    }

    public void setImageCount(String imageCount) {
        this.imageCount = imageCount;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Long getHomePageId() {
        return homePageId;
    }

    public void setHomePageId(Long homePageId) {
        this.homePageId = homePageId;
    }
}
