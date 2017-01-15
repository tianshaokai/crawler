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
            logger.error("解析网页{}失败: {}", url, e.toString());
        }
        long end = System.currentTimeMillis();
        logger.debug("解析网页时间: {}", (end - start)/1000 + "s.");
        return doc;
    }

    public List<ImageInfo> getImagePageInfo(TargetPage targetPage, String role) {

        List<ImageInfo> imageInfoList = new ArrayList<ImageInfo>();
        Document document = getDocument(targetPage.getUrl());
        if(document == null) {
            return imageInfoList;
        }
        Elements totalLinks = document.select(role);
        String totalPage = totalLinks.get(4).text();
        List<String> targetUrlList = new ArrayList<String>();
        for (int i = 1; i < Integer.parseInt(totalPage); i++) {
            if(i == 1) {
                String url = targetPage.getUrl();
                targetUrlList.add(url);
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
                logger.debug("url: " + imageInfo.getUrl());
                imageInfo.setCreateTime(new Date());
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
            String url = link.attr("href");
            if (duplicatedChecker.isDuplicated(url)) {
                logger.debug("已经爬取过该链接，url: {}", url);
                continue;
            }
            TargetPage targetPage = new TargetPage();
            targetPage.setUrl(url);
            targetPage.setTitle(link.text());
            targetPage.setCreateTime(new Date());
            targetPage.setHomePageId(homePage.getId());

            targetPageList.add(targetPage);
        }
        return targetPageList;
    }


    public List<TargetPage> getTargetPageLink2(HomePage homePage) {

        List<TargetPage> targetPageList = new ArrayList<TargetPage>();
        List<String> list = new ArrayList<String>();
        Document document = getDocument(homePage.getUrl());
        if(document == null) {
            return targetPageList;
        }

        Elements linktatall = document.select("span.pageinfo strong");
        System.out.println(linktatall.get(0).text());

        Elements linkurl = document.select("div.pagelist a");

        String[] baseurl = linkurl.get(0).attr("href").split("_");
        for (int i = 1; i <= Integer.parseInt(linktatall.get(0).text()); i++) {
//            if (i == 1) {
//                continue;
//            }
            String str = baseurl[0] + "_" + baseurl[1] + "_" + i + ".html";
            System.out.println(str);
            list.add(homePage.getUrl() + str);
        }

//        Elements links = document.select(homePage.getRole());
//        if(links == null) {
//            logger.error("未获取到数据");
//            return targetPageList;
//        }
//
//        for(Element link : links) {
//            TargetPage targetPage = new TargetPage();
//            targetPage.setUrl("http://www.dazui88.com" + link.attr("href"));
//            targetPage.setTitle(link.text());
//            System.out.println(targetPage.getUrl()+" "+targetPage.getTitle());
//            targetPageList.add(targetPage);
//        }

        for (String s : list) {
            System.out.println("加载分页网页： " + s);
            Document doc = getDocument(s);
            if(doc == null) {
                continue;
            }

            Elements links2 = doc.select(homePage.getRole());
            if(links2 == null) {
                logger.error("未获取到数据");
                continue;
            }

            for(Element link : links2) {
                TargetPage targetPage = new TargetPage();
                targetPage.setUrl("http://www.dazui88.com" + link.attr("href"));
                targetPage.setTitle(link.text());
                System.out.println(targetPage.getUrl()+" title: "+targetPage.getTitle());
                targetPageList.add(targetPage);
            }


        }

        return targetPageList;
    }

    public void getImagePage2(String url) {
        System.out.println(url);
        List<String> list = new ArrayList<String>();
        Document document = getDocument(url);
        if(document == null) {
            return;
        }
//        Elements links = document.select("div#efpBigPic img");
//        for (Element link : links) {
//            System.out.println(link.attr("src"));
//        }
        Elements elements = document.select("div.pagebreak1 a");
        String s = elements.get(0).text();
        System.out.println(s.substring(1, s.length() - 2));
        for (int i = 1; i <= Integer.parseInt(s.substring(1, s.length() - 2)); i++) {
            if (i == 1) {
                list.add(url);
                continue;
            }
            String str = url.substring(0, url.lastIndexOf("."));
            list.add(str + "_" + i + ".html");
        }
        for (String str : list) {
            Document doc = getDocument(str);
            if(doc == null) {
                return;
            }
            Elements linkList = doc.select("div#efpBigPic img");
            for (Element link : linkList) {
                System.out.println(link.attr("src"));
            }
        }
    }

    @Autowired
    public void setDuplicatedChecker(DuplicatedChecker duplicatedChecker) {
        this.duplicatedChecker = duplicatedChecker;
    }
}
