package com.tianshaokai.crawler.repository;

import com.tianshaokai.crawler.entity.HomePage;

public interface HomePageDao {
    int deleteByPrimaryKey(Integer id);

    int insert(HomePage record);

    int insertSelective(HomePage record);

    HomePage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HomePage record);

    int updateByPrimaryKey(HomePage record);
}