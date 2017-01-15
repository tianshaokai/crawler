package com.tianshaokai.crawler.core.task;

import com.tianshaokai.crawler.core.util.DigestHashUtil;
import com.tianshaokai.crawler.entity.TargetPage;
import com.tianshaokai.crawler.repository.TargetPageDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DuplicatedChecker {

    @Autowired
    private TargetPageDao targetPageDao;

    public boolean isDuplicated(String url) {
        String dstS = DigestHashUtil.hash(url);

        TargetPage results = targetPageDao.selectByHash(dstS);
        return results != null;
    }
}
