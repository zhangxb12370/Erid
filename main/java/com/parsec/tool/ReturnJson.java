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
package com.parsec.tool;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * 统一返回对象
 * @author tby
 */
public class ReturnJson<T> {

	private int status;
	private String msg;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long total;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer pageNo;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<T>list;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T result;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String debugMsg;
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


	public String getDebugMsg() {
		return debugMsg;
	}

	public int getStatus() {
		return status;
	}



	public String getMsg() {
		return msg;
	}



	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public List<T> getList() {
		return list;
	}

	public ReturnJson<T> setList(List<T> list) {
		this.list = list;
		return this;
	}

	public T getResult() {
		return result;
	}

	public ReturnJson<T> setResult(T result) {
		this.result = result;
		return this;
	}

	/**
	 * 返回列表
	 * @param list			列表集合
	 * @param totalCount	总数
	 * @param pageNo		页码
     */
	public ReturnJson setList(List list, Long totalCount, int pageNo){
		this.list=list;
		this.total=totalCount;
		this.pageNo=pageNo;
		return this;
	}



	public ReturnJson(){
		setSuccess(null);
	}

	
	/**
	 * 返回正常值，表示处理成功<br>
	 * 方 法 名：returnSuccess <br>
	 * 创 建 人：zpj <br>
	 * 创建时间：2015年8月17日 下午3:01:58 <br>
	 * 修 改 人： <br>
	 * 修改日期： <br>
	 * @param msg 自定义信息
	 * @return 
	 * ReturnJson
	 */
	public ReturnJson setSuccess(String msg){
		this.status=Success;
		this.msg=msg;
		return this;

	}


	/**
	 * 登录失败,返回
	 * @return
     */
	public ReturnJson setRelogin(){
		this.status=Relogin;
		this.msg="登录失效,请重新登录";
		return this;
	}
	
	/**
	 * 返回错误值，表示数据错误，业务处理失败<br>
	 * 方 法 名：returnFail <br>
	 * 创 建 人：zpj <br>
	 * 创建时间：2015年8月17日 下午3:02:17 <br>
	 * 修 改 人： <br>
	 * 修改日期： <br>
	 * @param msg 失败的原因
	 * @return 
	 * ReturnJson
	 */
	public ReturnJson setFail(String msg){
		this.status=Fail;
		this.msg=msg;
		return this;
	}


	/**
	 * 表示操作超时<br>
	 * 方 法 名：returnExpired <br>
	 * 创 建 人：zpj <br>
	 * 创建时间：2015年8月17日 下午3:02:38 <br>
	 * 修 改 人： <br>
	 * 修改日期： <br>
	 * @return 
	 * ReturnJson
	 */
	public ReturnJson setExpired(){
		this.status=Expired;
		this.msg="操作超时";
		return this;
	}

	/**
	 * 表示有异常抛出<br>
	 * 方 法 名：returnException <br>
	 * 创 建 人：zpj <br>
	 * 创建时间：2015年8月17日 下午3:02:41 <br>
	 * 修 改 人： <br>
	 * 修改日期： <br>
	 * @param e 异常的内容
	 * @return 
	 * ReturnJson
	 */
	public ReturnJson setException(Exception e){
		this.debugMsg=e.getLocalizedMessage();
		this.status=Exception;
		this.msg="服务器内部错误";
		return this;
	}

}
