package com.zero.travel.common.util;

import com.zero.travel.pojo.dto.RouteDTO;

import java.lang.reflect.Field;
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

    /**
     * 判断对象各属性是否为空
     * @param obj
     * @return
     * @throws Exception
     */
    public static boolean isAllFieldNull(Object obj) throws Exception{
        final Class aClass = obj.getClass();
        final Field[] declaredFields = aClass.getDeclaredFields();

        boolean flag = true;
        for (Field declaredField : declaredFields) {
            // 设置属性是可以访问的(私有的也可以)
            declaredField.setAccessible(true);

            final Object o = declaredField.get(obj);
            if (o != null){
                flag = false;
                break;
            }
        }
        return flag;
    }

    /*public static void main(String[] args) {

        try {
            RouteDTO routeDTO = new RouteDTO();
            RouteDTO dto = new RouteDTO();
            dto.setRouteName("Alice");

            System.out.println("routeDTO: "+isAllFieldNull(routeDTO));
            System.out.println("dto: "+isAllFieldNull(dto));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/
}
