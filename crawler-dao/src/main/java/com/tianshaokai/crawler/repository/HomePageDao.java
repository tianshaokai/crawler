package com.tianshaokai.crawler.repository;

import com.tianshaokai.crawler.entity.HomePage;

import java.util.List;

public interface HomePageDao {
    int deleteByPrimaryKey(Integer id);

    int insert(HomePage record);

    int insertSelective(HomePage record);

    List<HomePage> selectAllHomePage();

    HomePage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HomePage record);

    int updateByPrimaryKey(HomePage record);
}