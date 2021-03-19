package com.zero.travel.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author LJC
 * @version 1.0
 * @date 2020/12/8 16:34
 */
public class SystemUtils {

    /**
     * 重命名上传文件名
     * @param fileName
     * @return
     */
    public static String rename(String fileName){
        if (fileName == null){
            return null;
        }
        if ("".equals(fileName)){
            return "";
        }
        int index = fileName.lastIndexOf(".");
        String suffix = fileName.substring(index);

        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+new Random().nextInt(100) +suffix;

    }

    //TODO:判断对象各属性是否为空
    //public static
}
