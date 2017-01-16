package com.tianshaokai.crawler.web;

import com.google.gson.Gson;
import com.tianshaokai.crawler.entity.TargetPage;
import com.tianshaokai.crawler.service.TargetPageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/web")
public class TargetPageController {

    private Logger logger = LoggerFactory.getLogger(TargetPageController.class);

    @Autowired
    private TargetPageService targetPageService;

    @RequestMapping(value = "/getTargetPage")
    @ResponseBody
    public String getAllTargetPage() {
        List<TargetPage> targetPageList = targetPageService.getAllTargetPage();
        logger.debug("爬取的妹子类型：{}", targetPageList.size());
        Gson gson = new Gson();
        return gson.toJson(targetPageList);
    }
}
