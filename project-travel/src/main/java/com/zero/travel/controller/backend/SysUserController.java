package com.zero.travel.controller.backend;

import com.zero.travel.common.util.ValidateUtils;
import com.zero.travel.controller.CommonController;
import com.zero.travel.pojo.dto.SysUserDTO;
import com.zero.travel.pojo.entity.SysUser;
import com.zero.travel.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * @author LJC
 * @version 1.0
 * @date 2021/2/28 19:00
 */
@Controller
@RequestMapping("/backend/sysUser")
public class SysUserController extends CommonController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 登录
     * @param sysUserDTO
     * @param bindingResult
     * @param modelMap
     * @return
     */
    @PostMapping ("login")
    public String login(@Valid SysUserDTO sysUserDTO, BindingResult bindingResult, Model modelMap){
        //TODO:服务端表单校验
        if (bindingResult.hasErrors()){
            String checkResult = ValidateUtils.checkResult(bindingResult);
            modelMap.addAttribute("errorMsg",checkResult);
            return "/backend/login";
        }
        try {
            //TODO:登录验证
            SysUser sysUser = sysUserService.loginValidate(sysUserDTO);
            modelMap.addAttribute("currentUser",sysUser);
            return "/backend/main";
        } catch (Exception e) {
            log.error("登录验证异常:{}",e.getMessage());
            modelMap.addAttribute("errorMsg",e.getMessage());
            return "/backend/login";
        }
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping("logout")
    public String logout(){

        return "/backend/login";
    }



}
