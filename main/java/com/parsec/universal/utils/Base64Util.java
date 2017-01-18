package com.parsec.universal.utils;


import java.util.Base64;

/**
 * base64工具
 * @author zpj
 */
public class Base64Util {
	
	/**
	 * 解密base64字符串<br>
	 * 方 法 名：decode <br>
	 * 创 建 人：zpj <br>
	 * 创建时间：2015年11月16日 上午11:27:04 <br>
	 * 修 改 人： <br>
	 * 修改日期： <br>
	 * @param s
	 * @return 
	 * String
	 */
	public static String decode(String s){
		if(s == null)
			return new String(Base64.getDecoder().decode("null"));
		return new String(Base64.getDecoder().decode(s));
	}
	
	/**
	 * 加密base64字符串<br>
	 * 方 法 名：encode <br>
	 * 创 建 人：zpj <br>
	 * 创建时间：2015年11月16日 上午11:27:26 <br>
	 * 修 改 人： <br>
	 * 修改日期： <br>
	 * @param s
	 * @return 
	 * String
	 */
	public static String encode(String s){
		if(s == null)
			return new String(Base64.getEncoder().encode("null".getBytes()));
		return new String(Base64.getEncoder().encode(s.getBytes()));
	}
	
	public static void main(String[] args) {

		String testStr = "D:/IMG/2014/12/15/1418610105678.jpg";
		System.out.println("加密前：" + testStr);

		String encodeStr = encode(testStr);
		System.out.println("加密数据：" + encodeStr);

		String decodeStr = decode(encodeStr);
		System.out.println("解密数据：" + decodeStr);
	}
}