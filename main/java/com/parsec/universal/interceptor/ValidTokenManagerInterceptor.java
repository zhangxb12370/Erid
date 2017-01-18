/**
 *******************************************************************************
 * 
 * 项 目 名：maven_base <br>
 * 包 路 径：com.parsec.universal.interceptor.ValidTokenManagerInterceptor.java <br>
 * 模 块 名：<br>
 * 创 建 人：zpj <br>
 * 创建时间：2015年4月2日 下午11:00:22 <br>
 * 修改历史：<br>
 * 日期			修改人		描述 <br>
 * ----------------------------------------------------------------------------- <br>
 * 
 * 版    本：1.0 <br>
 * Copyright © 2014 重庆市秒差距科技有限公司. All Rights Reserved.
 *******************************************************************************  
 */
package com.parsec.universal.interceptor;


import com.parsec.eridanus.dao.AdminDao;
import com.parsec.eridanus.po.Admin;
import com.parsec.universal.utils.JWTUtil;
import com.parsec.universal.utils.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/** 
 * @author zpj
 */
@Component("validTokenMgr")
public class ValidTokenManagerInterceptor extends HandlerInterceptorAdapter {
	@Resource
	private AdminDao adminDao;

	//后处理，可修改modelAndView
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
						   ModelAndView modelAndView) throws Exception {

	}


	//预处理
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String token = request.getHeader("token");
		List<Admin> adminList = adminDao.findAll();
		for (Admin admin:adminList){
			if (StringUtil.isNotEmpty(token) && JWTUtil.decrypt(token,admin.getLoginName())){
				System.out.print("=======");
				return true;
			}
		}
		System.out.print("-------");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		return false;
	}




}









