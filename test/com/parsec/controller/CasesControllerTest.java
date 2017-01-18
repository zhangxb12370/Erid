package com.parsec.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.parsec.eridanus.controller.cases.CaseController;
import com.parsec.eridanus.po.Cases;
import com.parsec.tool.ReturnJson;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by zhangxb on 2016/11/15.
 */
public class CasesControllerTest extends BaseTest {
    @Resource
    private CaseController caseController;
    @Test
    public void testCase() throws JsonProcessingException {
        //添加案例 添加被投企业logo，测试成功
        Cases cases = new Cases();
        cases.setLogoUrl("www.taobao_new.com");
        cases.setRedirectUrl("www.taobao_new.com");
        ReturnJson returnJson = caseController.edit(cases);
        System.out.print(objectMapper.writeValueAsString(returnJson));
        Assert.assertTrue(returnJson.getStatus()==0);
//        //添加案例 不添加被投企业logo，测试失败
//        Cases cases1 = new Cases();
//        cases1.setRedirectUrl("www.taobao.com");
//        ReturnJson ret = caseController.edit(cases1);
//        Assert.assertTrue(ret.getStatus()==100);
//        //编辑案例
//        cases.setLogoUrl("www.baidu_new.com");
//        cases.setRedirectUrl("www.edit_new.com");
//        ReturnJson returnJson1 = caseController.edit(cases);
//        Assert.assertTrue(returnJson1.getStatus()==0);
        //查询案例列表
//        ReturnJson returnJson2 = caseController.findAll(1,10);
//        System.out.print(objectMapper.writeValueAsString(returnJson2));
//        Assert.assertTrue(returnJson2.getStatus()==0);
//        //删除案例
//        ReturnJson returnJson3 = caseController.delete(cases.getId());
//        Assert.assertTrue(returnJson3.getStatus()==0);
//        //删除之后查询该案例失败
//        ReturnJson returnJson4 = caseController.getById(cases.getId());
//        Assert.assertTrue(returnJson4.getStatus()==100);
    }

    @Override
    public void testBefore() {

    }

    @Override
    public void testAfter() {

    }
}
