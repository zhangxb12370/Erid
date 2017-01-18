package com.parsec.tool;


import com.parsec.universal.utils.DateUtil;
import com.parsec.universal.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Date;


/**
 * Created by tby on 2016/5/31.
 */
public class FileUpLoadUtil {
   private static Logger logger = LoggerFactory.getLogger(FileUpLoadUtil.class);
    /**
     * 生成文件的访问的相对url地址
     * @param offpix  文件的后缀名
     * @return
     */
    public  static String url(String offpix,String fileName ) throws InterruptedException {
        if (StringUtil.isEmpty(offpix)){
            return relativePath()+"/"+fileName;
        }
        return relativePath()+"/"+fileName+"."+offpix;
    }

    /**
     * 生成文件的绝对路径
     * @param containerPath  容器的绝对路径
     * @param offpix    文件的后缀名
     * @return
     */
    public  static String filePath (String containerPath,String offpix,String fileName ) throws InterruptedException {
        File parentFile=new File(containerPath);//获取容器的目录
        String parentPath=parentFile.getParent();//获取容器的父目录的路径
        String absolutePath=parentPath+relativePath();
        File absolutePathPathFile=new File(absolutePath);
        try{
            if(!absolutePathPathFile.exists()){absolutePathPathFile.mkdirs();} //如果路径不存在则创建路径
        }catch (RuntimeException e){
            logger.info(e.getMessage());
        }
        return parentPath+url(offpix,fileName);
    }

    private static String relativePath(){
        return "/files/"+ DateUtil.getDate(new Date(),"yyyy/MM/dd");
    }


    public static synchronized String fileName( ) throws InterruptedException {
        Thread.sleep(1);//停止1毫秒,以防生成重复的文件名称
        return String.valueOf(System.currentTimeMillis());
    }


}
