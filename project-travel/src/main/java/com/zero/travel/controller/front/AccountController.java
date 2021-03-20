package com.zero.travel.controller.front;

import com.zero.travel.common.enums.StatusCode;
import com.zero.travel.common.response.BaseResponse;
import com.zero.travel.common.util.ValidateUtils;
import com.zero.travel.controller.CommonController;
import com.zero.travel.pojo.dto.UserDTO;
import com.zero.travel.pojo.entity.User;
import com.zero.travel.pojo.vo.UserLoginVO;
import com.zero.travel.pojo.vo.UserModifyVO;
import com.zero.travel.pojo.vo.UserVO;
import com.zero.travel.service.front.AccountService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping(value = "/modifyPassword")
    @ResponseBody
    public BaseResponse modifyPassword(String oldPassword,String newPasswordOne,String newPasswordTwo,HttpSession session){
        try {
            if (ObjectUtils.isEmpty(oldPassword) || ObjectUtils.isEmpty(newPasswordOne) || ObjectUtils.isEmpty(newPasswordTwo)){
                return new BaseResponse(StatusCode.InvalidParams.getCode(),"密码不能为空");
            }
            if (!newPasswordOne.equals(newPasswordTwo)){
                return new BaseResponse(StatusCode.InvalidParams.getCode(),"两次输入的新密码不一致");
            }
            User currentUser = (User) session.getAttribute("currentUser");
            log.info(currentUser.toString());
            if (currentUser == null){
                return new BaseResponse(StatusCode.Fail.getCode(),"当前用户未登录");
            }
            if (!oldPassword.equals(currentUser.getPassword())){
                return new BaseResponse(StatusCode.InvalidParams.getCode(),"原密码不正确");
            }
            accountService.modifyPassword(currentUser.getUserId(),newPasswordOne);

            //更新session
            currentUser.setPassword(newPasswordOne);
            ///session.removeAttribute("currentUser");
            session.setAttribute("currentUser",currentUser);
            return new BaseResponse(StatusCode.Success.getCode(),"修改密码成功,下次登录时生效!");
        }catch (Exception e){
            log.error(e.toString());
            return new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
    }

    @RequestMapping("/info")
    public String userInfo(Model model,HttpSession session){
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null){
            return "front/main";
        }

        model.addAttribute("currUser",currentUser);

        return "front/userInfo";
    }

    @RequestMapping(value = "/modifyUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse modifyUserInfo(@Validated UserModifyVO userModifyVO,BindingResult result,HttpSession session){
        try {
            if (result.hasErrors()){
                final String checkResult = ValidateUtils.checkResult(result);
                return new BaseResponse(StatusCode.InvalidParams.getCode(),checkResult);
            }
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userModifyVO,userDTO);

            final User user = accountService.modifyInfo(userDTO);

            //TODO:更新session
            session.setAttribute("currentUser",user);

            return new BaseResponse(StatusCode.Success);
        }catch (Exception e){
            log.error(e.toString());
            return new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
    }

}
