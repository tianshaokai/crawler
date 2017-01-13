package com.tianshaokai.crawler.core.task;

import com.tianshaokai.crawler.core.config.SiteConfig;
import com.tianshaokai.crawler.entity.HomePage;
import com.tianshaokai.crawler.entity.ImageInfo;
import com.tianshaokai.crawler.entity.TargetPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class HomePageTask {

    private Logger logger = LoggerFactory.getLogger(HomePageTask.class);

    private SiteConfig config;
    private Crawler crawler;

    public void execute() {
        logger.info("爬虫程序 开始");
        List<HomePage> homePageList = config.getAllPage();

        List<TargetPage> allTargetPageList = crawlerTargetPage(homePageList);

        logger.debug("需要爬的总条数{}", allTargetPageList.size());



        for (TargetPage targetPage : allTargetPageList) {

            List<ImageInfo> imageInfoList = crawler.getImagePageInfo(targetPage, "div.pagenavi > a");

            logger.debug("爬取到的数量: {}", imageInfoList.size());
        }




    }

    private List<TargetPage> crawlerTargetPage(List<HomePage> homePageList) {

        List<TargetPage> allTargetPageList = new ArrayList<TargetPage>();
        for (HomePage homePage : homePageList) {
            List<TargetPage> targetPageList = crawler.getTargetPageLink(homePage);
            if(targetPageList == null) {
                continue;
            }
            allTargetPageList.addAll(targetPageList);
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
