package com.tianshaokai.crawler.core.config;

import com.tianshaokai.crawler.entity.HomePage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SiteConfig {

    public LinkedList<HomePage> getAllPage() {

        LinkedList<HomePage> list = new LinkedList<HomePage>();

//        HomePage homePage1 = new HomePage();
//        homePage1.setId(1);
//        homePage1.setUrl("http://www.mzitu.com/all");
//        homePage1.setSiteName("妹子图");
//        homePage1.setRole("ul.archives a[href]");
//        homePage1.setRole2("div.pagenavi > a");
//        homePage1.setRole3("div.main-image img");
//        homePage1.setDynamic(false);
//        homePage1.setStart(true);
//
//        list.add(homePage1);

//        HomePage homePage2 = new HomePage();
//        homePage2.setId(2l);
//        homePage2.setUrl("http://www.dazui88.com/mitao/");
//        homePage2.setSiteName("蜜桃社");
//        homePage2.setRole("div.lbtcimg1 p a[href]:has(span)");
//        homePage2.setDynamic(false);
//        homePage2.setStart(true);

        HomePage homePage3 = new HomePage();
        homePage3.setId(3);
        homePage3.setUrl("http://www.netbian.com/meinv/");
        homePage3.setSiteName("彼岸桌面");
        homePage3.setRole("div.list > ul > li > a");
        homePage3.setTotalPageRole("div.page a");
        homePage3.setDynamic(false);
        homePage3.setStart(true);

        list.add(homePage3);




        return list;
    }

}
