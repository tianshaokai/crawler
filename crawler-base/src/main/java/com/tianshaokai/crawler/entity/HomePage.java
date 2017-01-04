package com.tianshaokai.crawler.entity;

import java.io.Serializable;

/**
 *  设置等待爬取的网站信息
 */
public class HomePage implements Serializable {
    private Long id;
    private String siteName;			// 站点名称
    private String url;

    private Boolean dynamic;			// 动态
    private Boolean stop;				// 是否启动

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getDynamic() {
        return dynamic;
    }

    public void setDynamic(Boolean dynamic) {
        this.dynamic = dynamic;
    }

    public Boolean getStop() {
        return stop;
    }

    public void setStop(Boolean stop) {
        this.stop = stop;
    }
}
