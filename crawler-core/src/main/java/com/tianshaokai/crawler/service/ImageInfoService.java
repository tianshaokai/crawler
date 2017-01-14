package com.tianshaokai.crawler.service;

import com.tianshaokai.crawler.entity.ImageInfo;
import com.tianshaokai.crawler.repository.ImageInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("SpringJavaAutowiringInspection")
public class ImageInfoService {

    @Autowired
    private ImageInfoDao imageInfoDao;

    public void insertImageInfo(ImageInfo record) {
        imageInfoDao.insertSelective(record);
    }

}
