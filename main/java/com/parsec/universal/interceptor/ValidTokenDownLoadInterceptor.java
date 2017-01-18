package com.parsec.universal.interceptor;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by tby on 2016/8/25.
 */
@Component(value = "validTokendl")
public class ValidTokenDownLoadInterceptor extends HandlerInterceptorAdapter{



        //后处理，可修改modelAndView
        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                               ModelAndView modelAndView) throws Exception {

        }


        //预处理
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

            return true;
        }





}
