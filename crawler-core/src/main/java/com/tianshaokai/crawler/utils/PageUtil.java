package com.tianshaokai.crawler.utils;

import com.tianshaokai.crawler.entity.Page;

import java.util.List;

public class PageUtil {

    public static Page getPage(Integer pageNum, Integer pageSize, List list) {
        Page page = new Page();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setData(list);
        return page;
    }

}
