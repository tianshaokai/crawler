package com.tianshaokai.crawler.service;

import com.tianshaokai.crawler.entity.TargetPage;
import com.tianshaokai.crawler.repository.TargetPageDao;
import org.springframework.beans.factory.annotation.Autowired;

public class TargetPageService {

    @Autowired
    private TargetPageDao targetPageDao;

    public void insertTargetPage(TargetPage targetPage) {
        targetPageDao.insertSelective(targetPage);
    }

}
