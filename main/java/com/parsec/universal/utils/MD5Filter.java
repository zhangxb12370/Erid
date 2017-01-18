package com.parsec.universal.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5Filter {

	public static String getMd5Code(byte[] bytes) {
		String value = null;
		FileInputStream in = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(bytes);
			BigInteger bi = new BigInteger(1, md5.digest());
			value = bi.toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null!= in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value.toUpperCase();
	}

//	public static void main(String[] args) {
//		byte[] bytes = {1,2,3,4};
//		String v = MD5Filter.getMd5Code(bytes);
//		System.out.println(v);
//	}
}