package com.tianshaokai.crawler.web;


import com.google.gson.Gson;
import com.tianshaokai.crawler.entity.ImageInfo;
import com.tianshaokai.crawler.entity.Page;
import com.tianshaokai.crawler.service.ImageInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/web")
public class ImageInfoController {

    private Logger logger = LoggerFactory.getLogger(ImageInfoController.class);

    @Autowired
    private ImageInfoService imageInfoService;

    @RequestMapping(value = "/getImageInfo")
    @ResponseBody
    public String getAllImageInfo(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Page page = imageInfoService.getAllImageInfoByPage(pageNum, pageSize);
//        logger.debug("爬取的妹子类型：{}", imageInfoList.size());
        Gson gson = new Gson();
        return gson.toJson(page);
    }

}
