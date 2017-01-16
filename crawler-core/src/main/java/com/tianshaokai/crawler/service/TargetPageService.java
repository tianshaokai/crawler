package com.tianshaokai.crawler.service;

import com.tianshaokai.crawler.entity.TargetPage;
import com.tianshaokai.crawler.repository.TargetPageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@SuppressWarnings("SpringJavaAutowiringInspection")
public class TargetPageService {

    @Autowired
    private TargetPageDao targetPageDao;

    public void insertTargetPage(TargetPage targetPage) {
        targetPageDao.insertSelective(targetPage);
    }

    public List<TargetPage> getAllTargetPage() {
        return targetPageDao.selectAllTargetPage();
    }

    public TargetPage getTargetPage(Integer ID) {
        return targetPageDao.selectByPrimaryKey(ID);
    }

    public void updateTargetPage(TargetPage targetPage) {
        targetPageDao.updateByPrimaryKeySelective(targetPage);
    }

}
