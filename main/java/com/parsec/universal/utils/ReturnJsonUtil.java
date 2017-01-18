/**
 *******************************************************************************
 * 
 * 项 目 名：Columba <br>
 * 包 路 径：com.parsec.universal.utils.JsonUtil.java <br>
 * 模 块 名：<br>
 * 创 建 人：zpj <br>
 * 创建时间：2015年8月17日 下午2:55:44 <br>
 * 修改历史：<br>
 * 日期			修改人		描述 <br>
 * ----------------------------------------------------------------------------- <br>
 * 
 * 版    本：1.0 <br>
 * Copyright © 2014 重庆市秒差距科技有限公司. All Rights Reserved.
 *******************************************************************************  
 */
package com.parsec.universal.utils;

import org.json.JSONException;
import org.json.JSONObject;

/** 
 * @author zpj
 */
public class ReturnJsonUtil {
	
	/**
	 * 成功
	 */
	public final static int Success = 0;
	
	/**
	 * 失败
	 */
	public final static int Fail = 100;
	
	/**
	 * 业务执行超时
	 */
	public final static int Expired = 101;
	
	/**
	 * 异常
	 */
	public final static int Exception = 102;
	
	/**
	 * 需要重新登录
	 */
	public final static int Relogin = 104;
	
	//返回错误信息
	public static final String SC_MSG="msg";
	//返回状态
	public static final String SC_STATUS ="status";

	
	/**
	 * 返回正常值，表示处理成功<br>
	 * 方 法 名：returnSuccess <br>
	 * 创 建 人：zpj <br>
	 * 创建时间：2015年8月17日 下午3:01:58 <br>
	 * 修 改 人： <br>
	 * 修改日期： <br>
	 * @param obj
	 * @param msg
	 * @return 
	 * JSONObject
	 */
	public static JSONObject returnSuccess(JSONObject obj, String msg){
		try {
			obj.put(SC_STATUS, Success);
			obj.put(SC_MSG, msg);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	public static String returnSuccessString(JSONObject obj, String msg){
		return returnSuccess(obj, msg).toString();
	}
	
	
	public static JSONObject returnRelogin(){
		JSONObject obj = new JSONObject();
		try {
			obj.put(SC_STATUS, Relogin);
			obj.put(SC_MSG, "登录失效,请重新登录");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * 返回错误值，表示数据错误，业务处理失败<br>
	 * 方 法 名：returnFail <br>
	 * 创 建 人：zpj <br>
	 * 创建时间：2015年8月17日 下午3:02:17 <br>
	 * 修 改 人： <br>
	 * 修改日期： <br>
	 * @param obj
	 * @param msg
	 * @return 
	 * JSONObject
	 */
	public static JSONObject returnFail(JSONObject obj, String msg){
		try {
			obj.put(SC_STATUS, Fail);
			obj.put(SC_MSG, msg);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * 返回错误值
	 * @param obj
	 * @param msg
	 * @return
	 */
	public static String returnFailString(JSONObject obj, String msg){
		return returnFail(obj, msg).toString();
	}
	
	/**
	 * 表示操作超时<br>
	 * 方 法 名：returnExpired <br>
	 * 创 建 人：zpj <br>
	 * 创建时间：2015年8月17日 下午3:02:38 <br>
	 * 修 改 人： <br>
	 * 修改日期： <br>
	 * @param obj
	 * @param msg
	 * @return 
	 * JSONObject
	 */
	public static JSONObject returnExpired(JSONObject obj, String msg){
		try {
			obj.put(SC_STATUS, Expired);
			obj.put(SC_MSG, msg);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	public static String returnExpiredString(JSONObject obj, String msg){
		return  returnExpired(obj, msg).toString();
	}
	
	/**
	 * 表示有异常抛出<br>
	 * 方 法 名：returnException <br>
	 * 创 建 人：zpj <br>
	 * 创建时间：2015年8月17日 下午3:02:41 <br>
	 * 修 改 人： <br>
	 * 修改日期： <br>
	 * @param obj
	 * @param msg
	 * @return 
	 * JSONObject
	 */
	public static JSONObject returnException(JSONObject obj, String msg){
		try {
			obj.put(SC_STATUS, Exception);
			obj.put(SC_MSG, msg);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	public static  String returnExceptionString(JSONObject obj, String msg){
		return returnException(obj,msg).toString();
	}
	
	
}
