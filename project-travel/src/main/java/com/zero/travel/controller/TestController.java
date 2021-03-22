package com.zero.travel.controller;

import com.zero.travel.common.enums.StatusCode;
import com.zero.travel.common.response.BaseResponse;
import com.zero.travel.service.common.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author LJC
 * @version 1.0
 * @date 2021/3/22 12:11
 */
@Controller
@RequestMapping("/test")
public class TestController extends CommonController{

    @Resource
    private OssService ossService;

    @RequestMapping(value = "/oss")
    @ResponseBody
    public BaseResponse testOss(){
        try {
            ossService.uploadTest();
            return new BaseResponse(StatusCode.Success);
        }catch (Exception e){
            log.error(e.toString());
            return new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
    }

    @RequestMapping(value = "/oss/delete")
    @ResponseBody
    public BaseResponse testDeleteOss(){
        try {
            ossService.delete();
            return new BaseResponse(StatusCode.Success);
        }catch (Exception e){
            log.error(e.toString());
            return new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
    }
}
