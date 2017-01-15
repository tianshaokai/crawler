# 爬取妹子图
### crawler 的项目结构
1. crawler-core 是基础模块
2. crawler-main 是爬虫主程序
3. crawler-framework 是爬虫设置程序及浏览程序
4. 项目采用多模块Spring, Spring MVC, Mybatis

### 现在已经完成了初步爬取功能，以后还会继续完善
### crawler 的远大理想
1. 实现多线程爬取
2. 实现去重功能(已经在数据库中添加了Hash值判断,以后该放到Redis中)
3. web 端实现爬取网站的配置功能，实现爬取内容的浏览及搜索服务
4. 做一个Android 客户端供大家方便查看