package com.tianshaokai.crawler.core.task;

import com.tianshaokai.crawler.core.util.DigestHashUtil;
import com.tianshaokai.crawler.entity.ImageInfo;
import com.tianshaokai.crawler.entity.TargetPage;
import com.tianshaokai.crawler.repository.ImageInfoDao;
import com.tianshaokai.crawler.repository.TargetPageDao;
import org.springframework.beans.factory.annotation.Autowired;

public class DuplicatedChecker {

    @Autowired
    private TargetPageDao targetPageDao;

    @Autowired
    private ImageInfoDao imageInfoDao;

    public boolean isDuplicated(String url) {
        String dstS = DigestHashUtil.hash(url);

        TargetPage results = targetPageDao.selectByHash(dstS);
        return results != null;
    }

    public boolean isCrawlerImageInfo(String url) {
        String dstS = DigestHashUtil.hash(url);

        ImageInfo results = imageInfoDao.selectByHash(dstS);
        return results != null;
    }
}
