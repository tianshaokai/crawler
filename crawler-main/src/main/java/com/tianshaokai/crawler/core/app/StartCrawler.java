package com.tianshaokai.crawler.core.app;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

public class StartCrawler {

    static ApplicationContext springContext;

    static {
        springContext = App.getInstance().getContext();
    }

    public static void main(String[] args) {
        System.out.println("---------start----------");
    }


}
