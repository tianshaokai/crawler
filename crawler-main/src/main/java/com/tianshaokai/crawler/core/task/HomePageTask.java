package com.tianshaokai.crawler.core.task;

import com.tianshaokai.crawler.core.config.SiteConfig;
import com.tianshaokai.crawler.core.util.DigestHashUtil;
import com.tianshaokai.crawler.entity.HomePage;
import com.tianshaokai.crawler.entity.ImageInfo;
import com.tianshaokai.crawler.entity.TargetPage;
import com.tianshaokai.crawler.service.HomePageService;
import com.tianshaokai.crawler.service.ImageInfoService;
import com.tianshaokai.crawler.service.TargetPageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HomePageTask {

    private Logger logger = LoggerFactory.getLogger(HomePageTask.class);

    private SiteConfig config;
    private Crawler crawler;

    @Autowired
    private HomePageService homePageService;
    @Autowired
    private TargetPageService targetPageService;
    @Autowired
    private ImageInfoService imageInfoService;

    public void execute() {
        logger.info("爬虫程序 开始");

//        crawler.craw();

        //获取待爬取配置
        LinkedList<HomePage> homePageList = config.getAllPage();

        crawlerTargetUrl(homePageList);
    }

    private void crawlerTargetUrl(LinkedList<HomePage> homePageList) {
        if (homePageList.isEmpty()) {
            getImageUrl();
            return;
        }
        HomePage homePage = homePageList.removeFirst();

        List<HomePage> homePageTotalList = crawler.getHomePageTotalList(homePage);
        //获取待爬的目标地址
        List<TargetPage> allTargetPageList = crawlerTargetPage(homePageTotalList);

        logger.debug("需要爬的总条数：{}", allTargetPageList.size());

        if (allTargetPageList.isEmpty()) {
            getImageUrl();
        } else {
            for (TargetPage targetPage : allTargetPageList) {
                targetPageService.insertTargetPage(targetPage);
                logger.debug("待爬地址：{}, 已经插入数据库", targetPage.getUrl());
//
//            List<ImageInfo> imageInfoList = crawler.getImagePageInfo(targetPage);
//            if (imageInfoList != null && imageInfoList.isEmpty()) {
//                continue;
//            }
//            logger.debug("爬取到的数量: {}", imageInfoList.size());
//
//            for (ImageInfo image : imageInfoList) {
//                imageInfoService.insertImageInfo(image);
//            }
            }

            crawlerTargetPage(homePageList);
        }


    }

    private void getImageUrl() {
        List<TargetPage> targetPageList = targetPageService.getAllTargetPage();
        for (TargetPage targetPage : targetPageList) {

            List<ImageInfo> imageInfoList = crawler.getImagePageInfo2(targetPage);
            if (imageInfoList != null && imageInfoList.isEmpty()) {
                continue;
            }
            logger.debug("爬取到的数量: {}", imageInfoList.size());

            for (ImageInfo image : imageInfoList) {
                imageInfoService.insertImageInfo(image);
            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 爬取妹子图 待爬目标页
     * @param homePageList
     * @return
     */
    private List<TargetPage> crawlerTargetPage(List<HomePage> homePageList) {

        List<TargetPage> allTargetPageList = new ArrayList<TargetPage>();
        for (HomePage homePage : homePageList) {
//            List<TargetPage> targetPageList = crawler.getTargetPageLink(homePage);
            List<TargetPage> targetPageList = crawler.getTargetPageLink2(homePage);
            if(targetPageList == null) {
                continue;
            }
            allTargetPageList.addAll(targetPageList);

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return allTargetPageList;
    }


    @Autowired
    public void setConfig(SiteConfig config) {
        this.config = config;
    }

    public void setCrawler(Crawler crawler) {
        this.crawler = crawler;
    }
}
