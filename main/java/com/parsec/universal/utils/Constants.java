package com.parsec.universal.utils;

/**
 * 常量类
 * @author parsec
 * 2014-11-24
 */
public class Constants {



    // 回调微信支付域名，如果是微信应用和微信支付是一个项目的话，也是统一的微信域名，
    public static  String NetDomainName;

    // 微信跳转页面
    public static  String NetDomainPageURL;

	// JSON格式
    public static final String JSON_ENCODING = "application/json;charset=UTF-8";

	//上传文件绝对路径
    public static final String UPLOAD_FILE_PATH="/mnt/images/Equuleus";

    //jwt的保存路径
    public static final String JWT_KEY_FilePath = "/home/keys";

    //web后台超期时间，单位分钟
    public static final int WEB_MANAGER_API_EXPIRE = 60;

    //app应用超期时间，单位天
	public static  final int WEB_APPLICATION_API_EXPIRE = 31;

    //七牛配置
    public static  final String QINIU_ACCESS_KEY = "VK4CcDZUpiufEOt20Hl9nC4nRKhFgkqd4QTXLq8V";

    public static  final String QINIU_SECRET_KEY = "QNQx9MINyU4IrT9wZigVaTVcL5BoHCUJ7UhXYUyb";

    public static  final String QINIU_BUCKET = "parseccrux";

    public static  final String QINIU_HOST = "http://parseccrux.qiniudn.com/";

    //是否是测试环境
    public static final boolean isDebug = true;
}
