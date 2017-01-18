package com.parsec.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.parsec.eridanus.controller.index.IndexController;
import com.parsec.eridanus.po.Index;
import com.parsec.tool.ReturnJson;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by kevin on 2016/11/25.
 */
public class IndexControllerTest extends BaseTest {
    @Resource
    private IndexController indexController;
    @Test
    public void testEdit() throws JsonProcessingException {
        //添加幻灯片，设置幻灯片的url，添加成功
        Index index  = new Index();
        index.setImageUrl("www.tianmao.com");
        index.setRedirectUrl("www.baidu.com");
        ReturnJson returnJson = indexController.edit(index);
        Assert.assertTrue(returnJson.getStatus()==0);
        //添加幻灯片，不设置幻灯片url，添加失败
        Index index1  = new Index();
        index1.setRedirectUrl("www.baidu.com");
        ReturnJson returnJson1 = indexController.edit(index1);
        Assert.assertTrue(returnJson1.getStatus()==100);
        //编辑幻灯片
        index.setImageUrl("www.parsec.com.cn");
        index.setDetail("dfafsfda");
        ReturnJson returnJson2 = indexController.edit(index);
        Assert.assertTrue(returnJson2.getStatus()==0);
        //幻灯片列表
        ReturnJson returnJson3 = indexController.findAll();
        Assert.assertTrue(returnJson3.getStatus()==0);
        //删除幻灯片
        ReturnJson returnJson4 = indexController.deleteIndex(index.getId());
        Assert.assertTrue(returnJson4.getStatus()==0);
        //查找已经删除的幻灯片，失败
        ReturnJson returnJson5 = indexController.getById(index.getId());
        Assert.assertTrue(returnJson5.getStatus()==100);
    }


    @Override
    public void testBefore() {

    }

    @Override
    public void testAfter() {

    }
}
