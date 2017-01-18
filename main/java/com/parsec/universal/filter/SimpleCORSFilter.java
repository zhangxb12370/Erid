/**
 *******************************************************************************
 * Simple To Introduction
 * 
 * 项 目 名：Penaeidae <br>
 * 包 路 径：com.parsec.universal.filter.SimpleCORSFilter.java <br>
 * 模 块 名：(手动填写) <br>
 * 创 建 人：杨海超 <br>
 * 创建时间：2015年7月8日 下午3:48:37 <br>
 * 修改历史：<br>
 * 日期			修改人		描述 <br>
 * ----------------------------------------------------------------------------- <br>
 * 
 * 版    本：1.0 <br>
 * Copyright © 2015 重庆市秒差距科技有限公司. All Rights Reserved.
 *******************************************************************************  
 */
package com.parsec.universal.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yanghc
 */
public class SimpleCORSFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods","POST, GET, OPTIONS, DELETE, PUT");
			response.setHeader("Access-Control-Max-Age", "3600");
			response.setHeader("Access-Control-Allow-Headers","Cache-Control, Accept-Encoding, token, Origin, X-Requested-With, Content-Type, Accept, api_key, Authorization, x-auth-token");
//		    response.setHeader("Access-Control-Allow-Credentials", "true");
		filterChain.doFilter(request, response);
	}


}
