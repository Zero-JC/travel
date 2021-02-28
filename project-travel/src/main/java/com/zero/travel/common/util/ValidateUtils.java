package com.zero.travel.common.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * 统一的验参工具
 * @author LJC
 * @version 1.0
 * @date 2021/2/10 22:07
 */
public class ValidateUtils {

    public static String checkResult(BindingResult result){
        StringBuilder errorMessage = new StringBuilder();
        if (result!=null&& result.hasErrors()){
            List<ObjectError> allErrors = result.getAllErrors();
            //遍历数组，存储错误信息
            allErrors.forEach(objectError -> errorMessage.append(objectError.getDefaultMessage()).append("\n"));
        }

        return errorMessage.toString();
    }

}
