package com.parsec.universal.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangxb on 2016/11/4.
 */
public class FileUploadUtil {

    public static String relativePath(String subfix){
        String fileName = fileName();
        return "/upload_eridanus/"+new SimpleDateFormat("yyyy/MM/dd/").format(new Date())+fileName+subfix;
    }

    public static String getFilePath(String containerPath,String subfic){
        String parentPath = new File(containerPath).getParent();
        String absolutePath = parentPath + relativePath(subfic);
        File absolutePathFile = new File(absolutePath);
        if (!absolutePathFile.exists()){
            absolutePathFile.mkdirs();
        }
        return absolutePath;
    }

    private static String fileName(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.valueOf(System.currentTimeMillis());
    }
}
