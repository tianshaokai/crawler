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
    private String role;
    private String role2;
    private String role3;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole2() {
        return role2;
    }

    public void setRole2(String role2) {
        this.role2 = role2;
    }

    public String getRole3() {
        return role3;
    }

    public void setRole3(String role3) {
        this.role3 = role3;
    }
}
