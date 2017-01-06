package com.tianshaokai.crawler.core.task;

import com.tianshaokai.crawler.entity.HomePage;
import com.tianshaokai.crawler.entity.ImageInfo;
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
            doc = Jsoup.connect(url).userAgent(userAgent).timeout(30000).get();
        } catch (IOException e) {
            logger.error("解析网页{}失败: {}", url, e.toString());
        }
        return doc;
    }

    public List<ImageInfo> getImagePageInfo(TargetPage targetPage, String role) {

        List<ImageInfo> imageInfoList = new ArrayList<ImageInfo>();
        Document document = getDocument(targetPage.getUrl());
        Elements totalLinks = document.select(role);
        String totalPage = totalLinks.get(4).text();
        List<String> targetUrlList = new ArrayList<String>();
        for (int i = 1; i < Integer.parseInt(totalPage); i++) {
            if(i == 1) {
                continue;
            }
            String url = targetPage.getUrl() + "/" + i;
            targetUrlList.add(url);
        }

        for (String url : targetUrlList) {
            Document doc = getDocument(url);
            if(doc == null) {
                continue;
            }
            Elements links = doc.select("div.main-image img");

            for (Element link : links) {
                ImageInfo imageInfo = new ImageInfo();
                imageInfo.setUrl(link.attr("src"));
                imageInfo.setTargetId(targetPage.getId());

                imageInfoList.add(imageInfo);
            }
        }
        return imageInfoList;
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
            logger.error("未获取到数据");
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
