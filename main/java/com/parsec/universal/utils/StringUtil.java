/**
 *******************************************************************************
 * Simple To Introduction
 * 
 * 项 目 名：carina <br>
 * 包 路 径：com.parsec.carina.utils.StringUtil.java <br>
 * 模 块 名：(手动填写) <br>
 * 创 建 人：杨海超 <br>
 * 创建时间：2014年12月27日 上午11:21:55 <br>
 * 修改历史：<br>
 * 日期			修改人		描述 <br>
 * ----------------------------------------------------------------------------- <br>
 * 
 * 版    本：1.0 <br>
 * Copyright © 2014 重庆市秒差距科技有限公司. All Rights Reserved.
 *******************************************************************************  
 */
package com.parsec.universal.utils;


import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * @author 海超 (字符串工具类)
 */
public class StringUtil {
	/**
	 * 编码转换(中文乱码)<br>
	 * 方 法 名：getChinese <br>
	 * 创 建 人：杨海超 <br>
	 * 创建时间：2014-4-13 上午09:30:29 <br>
	 * 修 改 人： <br>
	 * 修改日期： <br>
	 * @param s 字符串
	 * @return String 字符串
	 */
	public static String getChinese(String s) {
		try {
			return new String(s.getBytes("utf-8"), "iso-8859-1");
		} catch (Exception e) {
			return s;
		}
	}

	/**
	 * 判断是否含有中文
	 * @param str
	 * @return
	 */
	public static boolean isContainChinese(String str) {

		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		return m.find();
	}

	/**
	 * 去除HTML标签<br>
	 * 方 法 名：clearHtml <br>
	 * 创 建 人：杨海超 <br>
	 * 创建时间：2014-4-13 上午09:30:01 <br>
	 * 修 改 人： <br>
	 * 修改日期： <br>
	 * @param htmlStr HTML字符串
	 * @return String 纯字符串
	 */
	public String clearHtml(String htmlStr) {
		String textStr = "";
		Pattern p_script;
		Matcher m_script;
		Pattern p_style;
		Matcher m_style;
		Pattern p_html;
		Matcher m_html;
		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
			String regEx_html = "<[^>]+>";
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll("");
			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll("");
			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll("");
			textStr = htmlStr.replaceAll(" ", "");
			textStr = htmlStr.replaceAll("<", "<");
			textStr = htmlStr.replaceAll(">", ">");
			textStr = htmlStr.replaceAll("®", "®");
			textStr = htmlStr.replaceAll("&", "&");
		} catch (Exception e) {
			// 不处理异常
		}
		return textStr;
	}
	
	
	public static String str;
	public static final String EMPTY_STRING = "";

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * 转换字节数组为16进制字串<br>
	 * 方 法 名：byteArrayToHexString <br>
	 * 创 建 人：杨海超 <br>
	 * 创建时间：2014-4-26 下午12:21:14 <br>
	 * 修 改 人： <br>
	 * 修改日期： <br>
	 * @param b 字节数组
	 * @return 16进制字串
	 */
	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	public static String MD5Encode(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes()));
		} catch (Exception ex) {
		}
		return resultString;
	}


	/**
	 * 如掉集合中的重复数据<br>
	 * 方 法 名：distinct <br>
	 * 创 建 人：杨海超 <br>
	 * 创建时间：2014-7-12 下午03:50:18 <br>
	 * 修 改 人： <br>
	 * 修改日期： <br>
	 * @param list
	 * @return List<String>
	 */
	public  static List<String> distinct(List<String> list) {
		int m = 0;
		List<String> strList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				if (list.get(i).trim().equals(list.get(j).trim())) {
					if (i != j) {
						m = j;
						list.remove(m);
						j--;
					} else {
						strList.add(list.get(i));
					}
				}
			}
		}
		return strList;
	}


	/**
	 * 判断是否为空，忽略null和“”<br>
	 * 方 法 名：isEmpty <br>
	 * 创 建 人：杨海超 <br>
	 * 创建时间：2015年1月15日 上午9:51:52 <br>
	 * 修 改 人： <br>
	 * 修改日期： <br>
	 * @param s
	 * @return boolean
	 */
	public static boolean isEmpty(String s) {
		if (s == null) {
			return true;
		} else return s.trim().equals("") || s.trim().equals("null");
	}


	/**
	 * 判断是否非空<br>
	 * 方 法 名：isNotEmpty <br>
	 * 创 建 人：杨海超 <br>
	 * 创建时间：2015年1月15日 上午9:51:30 <br>
	 * 修 改 人： <br>
	 * 修改日期： <br>
	 * @param str 待判断的字符串
	 * @return boolean 结果
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
	
	/**
	 * 将字符串null转换为""<br>
	 * 方 法 名：convertNullToBlank <br>
	 * 创 建 人：杨海超 <br>
	 * 创建时间：2015年1月15日 上午9:52:52 <br>
	 * 修 改 人： <br>
	 * 修改日期： <br>
	 * @param s
	 * @return String
	 */
	public static String convertNullToBlank(String s) {
		return null==s?"":s;
	}

	/**
	 * 判断是否是数字<br>
	 * 方 法 名：isNumber <br>
	 * 创 建 人：杨海超 <br>
	 * 创建时间：2015年1月15日 上午9:52:57 <br>
	 * 修 改 人： <br>
	 * 修改日期： <br>
	 * @param s
	 * @return boolean
	 */
	public static boolean isNumber(String s){
		
		try{
			float fla=Float.parseFloat(s);
		}catch(Exception ex){
			return false;
		}
		return true;
	}
	
	/**
	 * 判断是否相等，忽略null和“”<br>
	 * 方 法 名：ifEqualWithNull <br>
	 * 创 建 人：杨海超 <br>
	 * 创建时间：2015年1月15日 上午9:53:03 <br>
	 * 修 改 人： <br>
	 * 修改日期： <br>
	 * @param no1
	 * @param no2
	 * @return boolean
	 */
	public static boolean ifEqualWithNull(String no1, String no2) {
		if (isEmpty(no1) && isEmpty(no2)) {
			return true;
		} else return no1.equals(no2);
	}

	/**
	 * 判断是否相等，考虑null和“”为不同内容<br>
	 * 方 法 名：ifEqual <br>
	 * 创 建 人：杨海超 <br>
	 * 创建时间：2015年1月15日 上午9:53:11 <br>
	 * 修 改 人： <br>
	 * 修改日期： <br>
	 * @param no1
	 * @param no2
	 * @return boolean
	 */
	public static boolean ifEqual(String no1, String no2) {
		if (no1 == null && no2 == null) {
			return true;
		} else if (no1 != null && no2 == null) {
			return false;
		} else if (no1 == null && no2 != null) {
			return false;
		} else return no1.equals(no2);
	}


	/**
	 * 简单的随机生成大写字母+数字的编码
	 * @param length 编码长度
	 * @param isAllowI1O0 结果可包含IO10这四个易混淆字符
	 * @return
	 */
	public static   String generateCode(int length, int isAllowI1O0){
		Random rand = new Random();
		StringBuffer sbf = new StringBuffer();
		int i = 0;
		while (i < length) {
            int num = 0;
            if (rand.nextBoolean()) {
                num = rand.nextInt(10) + 48;
            } else {
                num = rand.nextInt(26) + 65;
            }
			//ord('0'), ord('1'), ord('I'), ord('O'), ord('A')=>(48, 49, 73, 79, 65)
			if (isAllowI1O0 == 0 && (num == 48 || num == 49 || num == 73 || num == 79)) {
				continue;
			}
            sbf.append(Character.toChars(num));
			i++;
		}
		return sbf.toString();
	}

	public static String generateCode(int length){
		return generateCode(length,0);
	}


}
