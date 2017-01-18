package com.parsec.universal.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.compression.CompressionCodecs;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;


/**
 * json web token
 * @author zpj
 */
public class JWTUtil {


	
	private static Key key;
	
	private static String ISSUER = "Parsec";
	
	static {
		
		
		key = (Key)readObjectFromFile(Constants.JWT_KEY_FilePath);
		if(key == null){
			key = MacProvider.generateKey();
			writeObjectToFile(key, Constants.JWT_KEY_FilePath);
		}
		
	}

	/**
	 * 返回超时分钟，一般用于后台管理
	 * @return
	 */
	public static Date expireMinute(){
		Calendar calr = Calendar.getInstance();
		calr.add(Calendar.MINUTE, Constants.WEB_MANAGER_API_EXPIRE);
		return calr.getTime();
	}

	/**
	 * 返回超时天数，一般用于客户APP应用
	 * @return
	 */
	public static  Date expireDay(){
		Calendar calr = Calendar.getInstance();
		calr.add(Calendar.DAY_OF_YEAR, Constants.WEB_APPLICATION_API_EXPIRE);
		return calr.getTime();
	}
	
	/**
	 * 生成JWT<br>
	 * 方 法 名：encrypt <br>
	 * 创 建 人：zpj <br>
	 * 创建时间：2015年11月30日 下午2:13:07 <br>
	 * 修 改 人： <br>
	 * 修改日期： <br>
	 * @param userId
	 * @param exp
	 * @return 
	 * String
	 */
	public static String encrypt(String userId, Date exp){

		return Jwts.builder().setIssuer(ISSUER).setAudience(userId).setExpiration(exp).compressWith(CompressionCodecs.GZIP).signWith(SignatureAlgorithm.HS512, key).compact();
	}
	
	/**
	 * 验证JWT<br>
	 * 方 法 名：decrypt <br>
	 * 创 建 人：zpj <br>
	 * 创建时间：2015年11月30日 下午2:13:26 <br>
	 * 修 改 人： <br>
	 * 修改日期： <br>
	 * @param token
	 * @param userId
	 * @return 
	 * boolean
	 */
	public static boolean decrypt(String token, String userId){
		
		try{
			Claims cls = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
			if(cls.getIssuer().equals(ISSUER) && cls.getAudience().equals(userId) )
				return true;
			else
				return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
     * 将对象保存在文件里
     *
     * @param obj
     * @param file
	 * @throws IOException
     */
    public static void writeObjectToFile(Object obj, String file){
    	try {

			if(FileUtils.verifyPath(file)){

				FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(obj);
				oos.close();
				fos.close();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        
    }

    /**
     * 从文件中读取对象
     *
     * @param file
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object readObjectFromFile(String file) {
        try {

            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
			Object obj = ois.readObject();
            ois.close();
            fis.close();
            return obj;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
    }
	

}
