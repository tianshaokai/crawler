package com.tianshaokai.crawler.repository;

import com.tianshaokai.crawler.entity.ImageInfo;

import java.util.List;

@MyBatisRepository
public interface ImageInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ImageInfo record);

    int insertSelective(ImageInfo record);

    ImageInfo selectByPrimaryKey(Integer id);

    List<ImageInfo> selectAllImageInfo();

    List<ImageInfo> selectAllImageInfoByPage();

    int updateByPrimaryKeySelective(ImageInfo record);

    int updateByPrimaryKey(ImageInfo record);

    ImageInfo selectByHash(String dstS);
}