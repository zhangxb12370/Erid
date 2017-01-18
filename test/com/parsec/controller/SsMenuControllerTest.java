package com.parsec.controller;

import com.parsec.eridanus.controller.menu.MenuController;
import com.parsec.eridanus.po.SsMenu;
import com.parsec.tool.ReturnJson;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by kevin on 2016/11/28.
 */
public class SsMenuControllerTest extends BaseTest {
    @Resource
    private MenuController menuController;
    @Test
    public void testFindAll(){
        ReturnJson ret = menuController.findAll();
        System.out.print(ret.getList());
    }
    @Test
    public void testEdit(){
        SsMenu ssMenu = new SsMenu();
        ssMenu.setUrlName("test");
        menuController.edit(ssMenu);
    }
    @Test
    public void testGetById(){
        menuController.getById(26);
    }
    @Override
    public void testBefore() {

    }

    @Override
    public void testAfter() {

    }
}
