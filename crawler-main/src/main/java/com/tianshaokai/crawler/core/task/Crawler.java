package com.tianshaokai.crawler.core.task;

import com.tianshaokai.crawler.core.util.DigestHashUtil;
import com.tianshaokai.crawler.entity.HomePage;
import com.tianshaokai.crawler.entity.ImageInfo;
import com.tianshaokai.crawler.entity.TargetPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  爬取的真正 实现类
 */
public class Crawler {

    private Logger logger = LoggerFactory.getLogger(Crawler.class);

    private DuplicatedChecker duplicatedChecker;

    private Document getDocument(String url) {
        long start = System.currentTimeMillis();
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.59 Safari/537.36";
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent(userAgent).timeout(60000).get();
        } catch (IOException e) {
            logger.error("解析网页 {} 失败: {}", url, e.toString());
        }
        long end = System.currentTimeMillis();
        logger.debug("解析网页时间: {}", (end - start)/1000 + "s.");
        return doc;
    }

    public List<ImageInfo> getImagePageInfo(TargetPage targetPage) {

        List<ImageInfo> imageInfoList = new ArrayList<ImageInfo>();
        Document document = getDocument(targetPage.getUrl());
        if(document == null) {
            return imageInfoList;
        }
        Elements totalLinks = document.select(targetPage.getRole());
        if (totalLinks.size() < 5) {
            return imageInfoList;
        }
        String totalPage = totalLinks.get(4).text();
        List<String> targetUrlList = new ArrayList<String>();
        for (int i = 1; i <= Integer.parseInt(totalPage); i++) {
            if(i == 1) {
                String url = targetPage.getUrl();
                targetUrlList.add(url);
                continue;
            }
            String url = targetPage.getUrl() + "/" + i;
            targetUrlList.add(url);
        }

        for (String url : targetUrlList) {
            logger.debug("爬取的目标url: {}", url);
            Document doc = getDocument(url);
            if(doc == null) {
                logger.error("爬取的目标, 失败的url: {}", url);
                continue;
            }
            Elements links = doc.select(targetPage.getRole2());

            for (Element link : links) {
                String imageUrl = link.attr("src");
                if (duplicatedChecker.isCrawlerImageInfo(imageUrl)) {
                    logger.warn("已经爬取过该链接，url: {}", url);
                    continue;
                }
                ImageInfo imageInfo = new ImageInfo();
                imageInfo.setUrl(imageUrl);
                imageInfo.setTargetUrl(targetPage.getUrl());
                imageInfo.setHash(DigestHashUtil.hash(imageUrl));

                imageInfo.setCreateTime(new Date());
                imageInfo.setTargetId(targetPage.getId());

                imageInfoList.add(imageInfo);
            }
        }
        return imageInfoList;
    }

    /**
     *  返回 所有妹子图 目标页
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
            String url = link.attr("href");
            if (duplicatedChecker.isDuplicated(url)) {
                logger.warn("已经爬取过该链接，url: {}", url);
                continue;
            }
            TargetPage targetPage = new TargetPage();
            targetPage.setUrl(url);
            targetPage.setTitle(link.text());
            targetPage.setCreateTime(new Date());
            targetPage.setHomePageId(homePage.getId());
            targetPage.setHash(DigestHashUtil.hash(url));

            targetPage.setRole(homePage.getRole2());
            targetPage.setRole2(homePage.getRole3());

            targetPageList.add(targetPage);
        }
        return targetPageList;
    }


    public List<TargetPage> getTargetPageLink2(HomePage homePage) {

        List<TargetPage> targetPageList = new ArrayList<TargetPage>();
        Document document = getDocument(homePage.getUrl());
        if(document == null) {
            return targetPageList;
        }

        Elements linktatall = document.select(homePage.getRole());
        if (linktatall == null) {
            return targetPageList;
        }

        StringBuffer stringBuffer = new StringBuffer();

        for(Element link : linktatall) {
            String url_target = link.attr("href");

            stringBuffer.delete(0, stringBuffer.length());
            String url = stringBuffer.append("http://www.netbian.com").append(url_target).toString();

            if (duplicatedChecker.isDuplicated(url)) {
                logger.warn("已经爬取过该链接，url: {}", url);
                continue;
            }

            TargetPage targetPage = new TargetPage();
            targetPage.setUrl(url);
            targetPage.setTitle(link.text());
            targetPage.setCreateTime(new Date());
            targetPage.setHomePageId(homePage.getId());
            targetPage.setHash(DigestHashUtil.hash(url));

//            targetPage.setRole(homePage.getRole2());
//            targetPage.setRole2(homePage.getRole3());

            targetPageList.add(targetPage);

        }
        return targetPageList;
    }

    public List<HomePage> getHomePageTotalList(HomePage homePage) {
        List<HomePage> homePageList = new ArrayList<>();

        Document document = getDocument(homePage.getUrl());
        if(document == null) {
            return homePageList;
        }

        Elements linkPage = document.select(homePage.getTotalPageRole());
        String totalPage = linkPage.get(5).text();
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 1; i <= Integer.parseInt(totalPage); i++) {
            if(i == 1) {
                homePageList.add(homePage);
                continue;
            }

            stringBuffer.delete(0, stringBuffer.length());

            HomePage homePageOther = new HomePage();

            String url = stringBuffer.append(homePage.getUrl()).append("index_").append(i).append(".htm").toString();
            homePageOther.setId(i);
            homePageOther.setUrl(url);
            homePageOther.setDynamic(homePage.getDynamic());
            homePageOther.setRole(homePage.getRole());
            homePageOther.setSiteName(homePage.getSiteName());
            homePageList.add(homePageOther);
        }
        return homePageList;
    }

    public List<ImageInfo> getImagePageInfo2(TargetPage targetPage) {
        List<ImageInfo> imageInfoList = new ArrayList<ImageInfo>();
        Document document = getDocument(targetPage.getUrl());
        if(document == null) {
            logger.error("爬取的目标, 失败的url: {}", targetPage.getUrl());
            return imageInfoList;
        }
        Elements links = document.select("div.pic > p > a");
        for (Element link : links) {
            String url = link.attr("href");
            if (duplicatedChecker.isDuplicated(url)) {
                logger.warn("已经爬取过该链接，url: {}", url);
                continue;
            }
            ImageInfo imageInfo = new ImageInfo();
            imageInfo.setUrl(url);
            imageInfo.setTargetUrl(targetPage.getUrl());
            imageInfo.setHash(DigestHashUtil.hash(url));
            imageInfo.setCreateTime(new Date());
            imageInfoList.add(imageInfo);
        }
        return imageInfoList;
    }

    public void craw() {
        String url = "https://www.mzitu.com/166998";

        Document document = getDocument(url);

        Elements totalLinks = document.select("div.pagenavi > a");
        String totalPage = totalLinks.get(4).text();
        List<String> targetUrlList = new ArrayList<String>();
        for (int i = 1; i <= Integer.parseInt(totalPage); i++) {
            if(i == 1) {
                targetUrlList.add(url);
                continue;
            }
            String url1 = url + "/" + i;
            targetUrlList.add(url1);
        }

        logger.debug("地址： " + targetUrlList.size());
    }

    @Autowired
    public void setDuplicatedChecker(DuplicatedChecker duplicatedChecker) {
        this.duplicatedChecker = duplicatedChecker;
    }
}
