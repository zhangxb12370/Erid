package com.parsec.eridanus.controller.admin;

import com.parsec.eridanus.dao.AdminDao;
import com.parsec.eridanus.po.Admin;
import com.parsec.tool.Constant;
import com.parsec.tool.ReturnJson;
import com.parsec.universal.utils.EncryptUtil;
import com.parsec.universal.utils.StringUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangxb on 2016/11/23.
 */
@RestController
public class AdminController {
    @Resource
    private AdminDao adminDao;

    /**
     * 注册管理员
     * @param admin
     * @return
     */
    @RequestMapping(value = "admin/register",method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
    public ReturnJson addAdmin(@RequestBody Admin admin){
        ReturnJson ret = new ReturnJson();
        Admin isExist = adminDao.getbyName(admin.getLoginName());
        if (isExist!=null){
            return ret.setFail("用户名已存在");
        }
        if (StringUtil.isEmpty(admin.getLoginName())){
            return ret.setFail("用户名为空");
        }
        if (StringUtil.isEmpty(admin.getPassword())){
            return ret.setFail("密码为空");
        }
        admin.setCreateTime(new Date());
        admin.setUpdateTime(new Date());
        adminDao.addAdmin(admin);
        return ret.setSuccess("添加成功");
    }

    /**
     * 修改密码
     * @param admin
     * @return
     */
    @RequestMapping(value = "admin/modify/pwd",method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
    public ReturnJson modifyPwd(@RequestBody Admin admin){
        ReturnJson ret = new ReturnJson();
        if(StringUtil.isEmpty(admin.getLoginName())){
            return ret.setFail("用户名为空");
        }
        if (StringUtil.isEmpty(admin.getPassword())){
            return ret.setFail("旧密码为空");
        }
        if (StringUtil.isEmpty(admin.getNewPwd())){
            return ret.setFail("新密码为空");
        }
        Admin existAdmin = adminDao.getbyName(admin.getLoginName());
        if (!existAdmin.getPassword().equals(admin.getPassword())){
            return ret.setFail("旧密码输入错误");
        }
        else {
            existAdmin.setPassword(admin.getNewPwd());
            existAdmin.setUpdateTime(new Date());
            adminDao.modifyPwd(existAdmin);
            return ret.setSuccess("密码修改成功");
        }
    }

    /**
     * 管理员列表
     * @return
     */
    @RequestMapping(value = "admin/list",method = RequestMethod.POST,produces = {Constant.PRODUCE})
    public ReturnJson findAll(){
        ReturnJson returnJson = new ReturnJson();
        List<Admin> adminList = adminDao.findAll();
        returnJson.setList(adminList);
        return returnJson.setSuccess("查询成功");
    }
}
