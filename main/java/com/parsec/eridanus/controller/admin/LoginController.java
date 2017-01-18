package com.parsec.eridanus.controller.admin;

import com.parsec.eridanus.dao.AdminDao;
import com.parsec.eridanus.po.Admin;
import com.parsec.tool.CheckCodeUtil;
import com.parsec.tool.CheckCoderTool;
import com.parsec.tool.ReturnJson;
import com.parsec.universal.utils.EncryptUtil;
import com.parsec.universal.utils.JWTUtil;
import com.parsec.universal.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by zhangxb on 2016/11/11.
 */
@RestController
public class LoginController {
    @Resource
    private AdminDao adminDao;
    @RequestMapping(value = "public/login",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    public ReturnJson login(String loginName,String password,String randomCode,String verificationCode,HttpServletRequest request){
        ReturnJson ret = new ReturnJson();
        if (StringUtil.isEmpty(randomCode)){
            return ret.setFail("随机数为空");
        }
        if (StringUtil.isEmpty(verificationCode)){
            return ret.setFail("验证码为空");
        }
        if (StringUtil.isEmpty(loginName)){
            return ret.setFail("登录名为空");
        }
        if (StringUtil.isEmpty(password)){
            return ret.setFail("用户名为空");
        }
        if (CheckCodeUtil.validateCode(request,randomCode,verificationCode)){
            Admin existAdmin = adminDao.getbyName(loginName);
            if (existAdmin==null){
                return ret.setFail("该用户不存在");
            }
            if (!existAdmin.getPassword().equals(password)){
                return ret.setSuccess("密码输入错误");
            }
            else  {
                ret.setResult(new HashMap<String,String>(){{
                    put("token",JWTUtil.encrypt(loginName,JWTUtil.expireMinute()));
                    put("adminId",existAdmin.getId().toString());
                    put("loginName",loginName);
                }});
                return ret.setSuccess("登录成功");
            }
        }
        return ret.setFail("验证码输入错误");
    }



    @RequestMapping(value = "public/code",method = RequestMethod.GET)
    public void getCheckCode(HttpServletRequest request,HttpServletResponse response,String randomCode){
        try{
             CheckCodeUtil.getCode(request,response,randomCode);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }



}
