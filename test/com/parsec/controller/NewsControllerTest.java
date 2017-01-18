package com.parsec.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.parsec.eridanus.controller.article.ArticleController;
import com.parsec.eridanus.controller.news.NewsController;
import com.parsec.eridanus.po.Article;
import com.parsec.eridanus.po.News;
import com.parsec.tool.ReturnJson;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by zhangxb on 2016/11/15.
 */
public class NewsControllerTest extends BaseTest {
    @Resource
    private NewsController newsController;
    @Resource
    private ArticleController articleController;
    @Test
    public void testEdit() throws JsonProcessingException {
        //设置title、content添加成功
        News news = new News();
        news.setTitle("testtotest");
        news.setSubTitle("subtitle");
        news.setContent("测试内容测试内容测试内容测试内容测试内容测试内容测试内容");
        ReturnJson returnJson = newsController.edit(news);
        Assert.assertTrue(returnJson.getStatus()==0);
        //不设置title添加失败
        News news1 = new News();
        news1.setContent("测试内容测试内容测试内容测试内容测试内容测试内容测试内容");
        ReturnJson returnJson1 = newsController.edit(news1);
        Assert.assertTrue(returnJson1.getStatus()==100);
        //设置title、content修改成功
        news.setTitle("测试");
        news.setSubTitle("副标题");
        news.setContent("测试内容测试内容测试内容测试内容测试内容测试内容测试内容");
        ReturnJson returnJson2 = newsController.edit(news);
        Assert.assertTrue(returnJson2.getStatus()==0);
        //新闻列表
        ReturnJson ret = newsController.findByPage(1,10);
        System.out.print(objectMapper.writeValueAsString(ret));
        Assert.assertTrue(ret.getStatus()==0);
        //删除新闻
        ReturnJson returnJson3 = newsController.delete(news.getId());
        Assert.assertTrue(returnJson3.getStatus()==0);
        //查询删除之后的新闻，失败
        ReturnJson returnJson4 = newsController.getById(news.getId());
        Assert.assertTrue(returnJson4.getStatus()==100);
    }

    @Override
    public void testBefore() {

    }

    @Override
    public void testAfter() {

    }
}
