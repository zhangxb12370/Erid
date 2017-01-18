package com.parsec.universal.utils;

import java.io.*;
import java.util.Date;

/**
 * 文件操作帮助类
 * 
 * @author chenyong
 * 
 */
public class FileUtils {

	private static final boolean IS_DAY= true;
	
	/**
	 * 保存文件
	 * 
	 * @param file 文件数据流
	 * @param fileName 传入文件名称
	 * @return 返回文件相对路径
	 */
	public static String saveFile(byte[] file, String fileName) {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File f = null;
		String path = null;
		try {
			// 得到保存路径
			path = createPath(IS_DAY);
			f = new File(path);
			// 判断文件夹是是否存在 不存在就创建
			if (!f.exists()) {
				f.mkdirs();
			}
			// 构建文件完整路径
			f = new File(path+"/" + fileName);
			fos = new FileOutputStream(f);
			bos = new BufferedOutputStream(fos);
			bos.write(file);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return path+"/" + fileName;
	}

	/**
	 * 保存文件
	 * 
	 * @param file 文件
	 * @param fileName 传入文件名称
	 * @return 返回文件相对路径
	 */
	public static String saveFile(File file, String fileName) {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		FileInputStream fis = null;
		File f = null;
		String path = null;
		try {
			// 得到保存路径
			path = createPath(IS_DAY);

			f = new File(path);
			// 判断文件夹是是否存在 不存在就创建
			if (!f.exists()) {
				f.mkdirs();
			}
			// 构建文件完整路径
			f = new File(path+"/" + fileName);
			fos = new FileOutputStream(f);
			bos = new BufferedOutputStream(fos);

			fis = new FileInputStream(file);
			byte[] b = new byte[1024];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return path+"/" + fileName;
	}

	/**
	 * <br>
	 * 方 法 名：createPath <br>
	 * 创 建 人：chenyong <br>
	 * 创建时间：2015年3月18日 下午4:28:59 <br>
	 * 修 改 人： <br>
	 * 修改日期： <br>
	 * @param isDay 是否按照天数创建  false 按照月创建
	 * @return String
	 */
	public static String createPath(boolean isDay) {
		// 取得当前日期
		Date date = new Date();
		// 构建保存路径: 按照 年/月/日 创建文件夹
		StringBuffer path = new StringBuffer(Constants.UPLOAD_FILE_PATH);
		if(isDay){
			path.append("/" + DateUtil.getDate(date, "yyyy/MMdd"));
		}else{
			path.append("/" + DateUtil.getDate(date, "yyyy/MM"));
		}
		return path.toString();
	}

	/**
	 * 文件转换为byte[]
	 * @param file 传入文件
	 * @return byte[]
	 */
	public static byte[] fileToByte(File file) {
		byte[] buffer = null;
		FileInputStream fis = null;
		ByteArrayOutputStream bos = null;
		try {
			fis = new FileInputStream(file);
			bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return buffer;
	}

	/**
	 * 验证路径是否有效，如果没有目录会自动创建目录，
	 * @param path
	 * @return 路径无效返回false， 返回true路径有效
	 */
	public static boolean verifyPath(String path){
		File file = new File(path);
		File dir = file.getParentFile();
		if (!dir.exists()){
			return dir.mkdirs();
		}
		return true;
	}


	public static void main(String[] args) throws Exception {

//		File file = new
//		System.out.println(saveFile(txt.getBytes("UTF8"), new Date().getTime()+ ".txt"));
//		System.out.println(saveFile(new File("D:\\待选086.jpg"),new Date().getTime() + ".jpg"));
	}
}
