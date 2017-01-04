package com.tianshaokai.crawler.core.app;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class StartCrawler {

    public static void main(String[] args) {

        try {
            Document doc = Jsoup.connect("http://www.mzitu.com/all").get();
            Elements links = doc.select("ul.archives a[href]");
            for (Element link : links) {
                System.out.println(link.attr("href") + " link.text() = " + link.text());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
