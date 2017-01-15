package com.tianshaokai.crawler.entity;

import java.io.Serializable;
import java.util.Date;

public class TargetPage implements Serializable {
    private Integer id;
    private String url;
    private String title;
    private Date createTime;
    private String hash;
    private Integer homePageId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getHomePageId() {
        return homePageId;
    }

    public void setHomePageId(Integer homePageId) {
        this.homePageId = homePageId;
    }
}
