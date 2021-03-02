package com.zero.travel.controller.backend;

import com.zero.travel.common.util.ValidateUtils;
import com.zero.travel.pojo.dto.SysUserDTO;
import com.zero.travel.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class SysUserController {

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
    public String login(@Valid SysUserDTO sysUserDTO, BindingResult bindingResult,ModelMap modelMap){
        //TODO:表单校验
        if (bindingResult.hasErrors()){
            String checkResult = ValidateUtils.checkResult(bindingResult);
            modelMap.addAttribute("errorMsg",checkResult);
            return "/backend/login";
        }
        try {
            //TODO:登录验证
            sysUserService.loginValidate(sysUserDTO);

            return "/backend/main";
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute("errorMsg",e.getMessage());
            return "/backend/login";
        }
    }


}
