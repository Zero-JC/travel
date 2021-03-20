package com.zero.travel.controller.front;

import com.zero.travel.common.enums.StatusCode;
import com.zero.travel.common.response.BaseResponse;
import com.zero.travel.common.util.ValidateUtils;
import com.zero.travel.controller.CommonController;
import com.zero.travel.pojo.dto.UserDTO;
import com.zero.travel.pojo.entity.User;
import com.zero.travel.pojo.vo.UserLoginVO;
import com.zero.travel.pojo.vo.UserVO;
import com.zero.travel.service.front.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 *
 * @author LJC
 * @version 1.0
 * @date 2021/3/20 14:25
 */
@Controller
@RequestMapping("/front/account")
public class AccountController extends CommonController {

    @Autowired
    private AccountService accountService;


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse login(@Validated UserLoginVO userLoginVO,BindingResult result, HttpSession session){
        try {
            if (result.hasErrors()){
                String checkResult = ValidateUtils.checkResult(result);
                return new BaseResponse(StatusCode.InvalidParams.getCode(),checkResult);
            }
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userLoginVO,userDTO);
            User currentUser = accountService.login(userDTO);
            session.setAttribute("currentUser",currentUser);

            return new BaseResponse(StatusCode.LoginSuccess);
        } catch (Exception e) {
            log.error(e.toString());
            return new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
    }

    @RequestMapping(value = "/logout")
    @ResponseBody
    public BaseResponse logout(HttpSession session){
        try {
            session.removeAttribute("currentUser");
            return new BaseResponse(StatusCode.Success);
        }catch (Exception e){
            log.error(e.toString());
            return new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
    }

    @RequestMapping(value = "/register")
    @ResponseBody
    public BaseResponse register(@Validated UserVO userVO,BindingResult result){
        try {
            if (result.hasErrors()){
                final String checkResult = ValidateUtils.checkResult(result);
                return new BaseResponse(StatusCode.InvalidParams.getCode(),checkResult);
            }
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userVO,userDTO);

            accountService.register(userDTO);

            return new BaseResponse(StatusCode.Success);
        }catch (Exception e){
            log.error(e.toString());
            return new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
    }


}
