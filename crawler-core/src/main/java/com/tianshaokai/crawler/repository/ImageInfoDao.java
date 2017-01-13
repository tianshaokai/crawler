package com.tianshaokai.crawler.repository;

import com.tianshaokai.crawler.entity.ImageInfo;

@MyBatisRepository
public interface ImageInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ImageInfo record);

    int insertSelective(ImageInfo record);

    ImageInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImageInfo record);

    int updateByPrimaryKey(ImageInfo record);
}