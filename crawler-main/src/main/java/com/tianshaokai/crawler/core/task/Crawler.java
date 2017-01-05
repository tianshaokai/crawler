package com.tianshaokai.crawler.core.task;

import com.tianshaokai.crawler.entity.HomePage;
import com.tianshaokai.crawler.entity.TargetPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *  爬取的真正 实现类
 */
public class Crawler {

    private Logger logger = LoggerFactory.getLogger(Crawler.class);

    private Document getDocument(String url) {
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.59 Safari/537.36";
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.mzitu.com/83048").userAgent(userAgent).timeout(1000).get();
        } catch (IOException e) {
            logger.error("解析网页{}失败: {}", url, e.toString());
        }
        return doc;
    }

    public void getTargetPageInfo(TargetPage targetPage, String role) {

        Document document = getDocument(targetPage.getUrl());
        Elements totalLinks = document.select("div.pagenavi > a");
        String totalPage = totalLinks.get(4).text();
        int total = Integer.parseInt(totalPage);
        for (int i = 1; i < total; i++) {


        }

        Elements links = document.select("div.main-image img");

    }

    /**
     *  返回 所有子url 信息
     * @return
     */
    public List<TargetPage> getTargetPageLink(HomePage homePage) {

        List<TargetPage> targetPageList = new ArrayList<TargetPage>();
        Document document = getDocument(homePage.getUrl());
        if(document == null) {
            return targetPageList;
        }

        Elements links = document.select(homePage.getRole());
        if(links == null) {
            return targetPageList;
        }

        for(Element link : links) {
            TargetPage targetPage = new TargetPage();
            targetPage.setUrl(link.attr("href"));
            targetPage.setTitle(link.text());

            targetPage.setHomePageId(homePage.getId());

            targetPageList.add(targetPage);
        }
        return targetPageList;
    }



}
