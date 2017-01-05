package com.tianshaokai.crawler.core.app;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class StartCrawler {

    public static void main(String[] args) {

        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.59 Safari/537.36";
        try {
//            Document doc = Jsoup.connect("http://www.mzitu.com/all").userAgent(userAgent).timeout(1000).get();
//            Elements links = doc.select("ul.archives a[href]");
//            for (Element link : links) {
//                System.out.println(link.attr("href") + " link.text() = " + link.text());
//            }

            Document doc = Jsoup.connect("http://www.mzitu.com/83048").userAgent(userAgent).timeout(1000).get();

            Elements links = doc.select("div.main-image img");
            for (Element link : links) {
                System.out.println(link.attr("src"));
            }
//
//            Document doc2 = Jsoup.connect("http://www.mzitu.com/83048/2").userAgent(userAgent).timeout(1000).get();
//
//            Elements links2 = doc2.select("div.main-image img");
//            for (Element link : links2) {
//                System.out.println(link.attr("src"));
//            }

            //Document doc = Jsoup.connect("http://www.mzitu.com/83048").userAgent(userAgent).timeout(1000).get();

//            Elements links = doc.select("div.pagenavi span");
//            System.out.println(links.get(6).text());

//            Elements links = doc.select("div.pagenavi > a");
//            System.out.println(links.get(4).text());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
