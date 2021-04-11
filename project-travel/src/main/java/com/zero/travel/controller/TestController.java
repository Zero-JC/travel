package com.zero.travel.controller;

import com.zero.travel.common.enums.StatusCode;
import com.zero.travel.common.response.BaseResponse;
import com.zero.travel.service.common.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author LJC
 * @version 1.0
 * @date 2021/3/22 12:11
 */
@Controller
@RequestMapping("/test")
@Api(tags = "测试接口")
public class TestController extends CommonController{

    @Resource
    private OssService ossService;

    @ApiOperation("test oss upload Api")
    @GetMapping(value = "/oss")
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

    @ApiOperation("test oss delete Api")
    @GetMapping(value = "/oss/delete")
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

    @ApiOperation("test response Api one")
    @PostMapping(value = "/test01/{str}")
    @ResponseBody
    public BaseResponse test01(@PathVariable String str){
        BaseResponse baseResponse = new BaseResponse(StatusCode.Success);
        baseResponse.setData(str);
        return baseResponse;
    }

    @ApiOperation("test response Api two")
    @PostMapping(value = "/test02/{num}")
    @ResponseBody
    public BaseResponse test02(@PathVariable Integer num){
        if (num < 10){
            return new BaseResponse(StatusCode.Success,"参数小于10");
        }else {
            return new BaseResponse(StatusCode.Success,"参数大于等于10");
        }
    }
}
