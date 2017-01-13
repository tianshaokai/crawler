package com.tianshaokai.crawler.service;

import com.tianshaokai.crawler.entity.HomePage;
import com.tianshaokai.crawler.repository.HomePageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomePageService {

    @Autowired
    private HomePageDao homePageDao;

    public List<HomePage> getAllHomePage() {
        List<HomePage> homePageList = homePageDao.selectAllHomePage();
        return homePageList;
    }

}
