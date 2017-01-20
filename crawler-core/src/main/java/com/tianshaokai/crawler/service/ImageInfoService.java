package com.tianshaokai.crawler.service;

import com.tianshaokai.crawler.entity.ImageInfo;
import com.tianshaokai.crawler.entity.Page;
import com.tianshaokai.crawler.entity.TargetPage;
import com.tianshaokai.crawler.repository.ImageInfoDao;
import com.tianshaokai.crawler.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@SuppressWarnings("SpringJavaAutowiringInspection")
public class ImageInfoService {

    @Autowired
    private ImageInfoDao imageInfoDao;

    public void insertImageInfo(ImageInfo record) {
        imageInfoDao.insertSelective(record);
    }

    public List<ImageInfo> getAllImageInfo() {
        return imageInfoDao.selectAllImageInfo();
    }

    public Page getAllImageInfoByPage(Integer pageNum, Integer pageSize) {
        List<ImageInfo> imageInfoList = imageInfoDao.selectAllImageInfoByPage();
        Page page = PageUtil.getPage(pageNum, pageSize, imageInfoList);
        return page;
    }

    public ImageInfo getImageInfo(Integer ID) {
        return imageInfoDao.selectByPrimaryKey(ID);
    }

    public void updateImageInfo(ImageInfo imageInfo) {
        imageInfoDao.updateByPrimaryKeySelective(imageInfo);
    }

}
