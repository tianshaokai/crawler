package com.tianshaokai.crawler.core.config;

import com.tianshaokai.crawler.entity.HomePage;

import java.util.ArrayList;
import java.util.List;

public class SiteConfig {

    public List<HomePage> getAllPage() {

        List<HomePage> list = new ArrayList<HomePage>();
        HomePage homePage1 = new HomePage();
        homePage1.setId(1l);
        homePage1.setUrl("http://www.mzitu.com/all");
        homePage1.setSiteName("妹子图");
        homePage1.setRole("ul.archives a[href]");
        homePage1.setDynamic(false);
        homePage1.setStart(true);

        list.add(homePage1);
        return list;
    }

}
