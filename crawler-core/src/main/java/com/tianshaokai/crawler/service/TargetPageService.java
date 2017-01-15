package com.tianshaokai.crawler.service;

import com.sun.xml.internal.bind.v2.model.core.ID;
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

    public TargetPage getAllTargetPage(Integer ID) {
        return targetPageDao.selectByPrimaryKey(ID);
    }

    public void updateTargetPage(TargetPage targetPage) {
        targetPageDao.updateByPrimaryKeySelective(targetPage);
    }

}
