package com.tianshaokai.crawler.repository;

import com.tianshaokai.crawler.entity.TargetPage;

@MyBatisRepository
public interface TargetPageDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TargetPage record);

    int insertSelective(TargetPage record);

    TargetPage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TargetPage record);

    int updateByPrimaryKey(TargetPage record);
}