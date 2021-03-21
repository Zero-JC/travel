package com.zero.travel.controller.front;

import com.zero.travel.common.enums.StatusCode;
import com.zero.travel.common.response.BaseResponse;
import com.zero.travel.common.util.ValidateUtils;
import com.zero.travel.controller.CommonController;
import com.zero.travel.mapper.SellerMapper;
import com.zero.travel.pojo.dto.UserDTO;
import com.zero.travel.pojo.entity.FavoriteKey;
import com.zero.travel.pojo.entity.Seller;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 *
 * @author LJC
 * @version 1.0
 * @date 2021/3/20 14:25
 */
@RestController
@RequestMapping("/front/account")
public class AccountController extends CommonController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private SellerMapper sellerMapper;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
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



    /**
     * 会员中心-修改用户详情
     * @param userModifyVO
     * @param result
     * @param session
     * @return
     */
    @RequestMapping(value = "/modifyUserInfo",method = RequestMethod.POST)
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

    /**
     * 查询商家信息
     * @param sellerId
     * @return
     */
    @RequestMapping(value = "/sellerInfo")
    @ResponseBody
    public BaseResponse sellerInfo(Integer sellerId){
        try {
            Seller seller = sellerMapper.selectByPrimaryKey(sellerId);
            if (seller == null){
                return new BaseResponse(StatusCode.Fail.getCode(),"商家不存在");
            }
            return new BaseResponse(StatusCode.Success,seller);
        }catch (Exception e){
            log.error(e.toString());
            return new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
    }

    /**
     * 线路详情-收藏线路
     * @param routeId
     * @param session
     * @return
     */
    @RequestMapping(value = "/addFavorite")
    public BaseResponse addFavorite(Integer routeId,HttpSession session){
        try {
            if (ObjectUtils.isEmpty(routeId)){
                return new BaseResponse(StatusCode.InvalidParams);
            }
            User currentUser = (User) session.getAttribute("currentUser");
            if (currentUser == null){
                return new BaseResponse(StatusCode.Fail.getCode(),"当前没有用户登录,请先登录!");
            }
            FavoriteKey favoriteKey = new FavoriteKey();
            favoriteKey.setRouteId(routeId);
            favoriteKey.setUserId(currentUser.getUserId());
            accountService.addFavorite(favoriteKey);

            return new BaseResponse(StatusCode.Success);
        }catch (Exception e){
            log.error(e.toString());
            return new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
    }

    /**
     * 删除收藏线路
     * @param routeId
     * @return
     */
    @RequestMapping(value = "/deleteFavorite")
    public BaseResponse deleteFavorite(@RequestParam(name = "routeId") Integer routeId,HttpSession session){
        try {
            User currentUser = (User) session.getAttribute("currentUser");
            if (currentUser == null){
                return new BaseResponse(StatusCode.Fail.getCode(),"当前没有用户登录,请先登录!");
            }

            accountService.deleteFavorite(routeId,currentUser.getUserId());

            return new BaseResponse(StatusCode.Success);
        }catch (Exception e){
            log.error(e.toString());
            return new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
    }

    /**
     * 清除当前用户所有收藏
     * @return
     */
    @RequestMapping(value = "/deleteAllFavorite")
    public BaseResponse deleteAllFavorite(HttpSession session){
        try {
            User currentUser = (User) session.getAttribute("currentUser");
            if (currentUser == null){
                return new BaseResponse(StatusCode.Fail.getCode(),"当前没有用户登录,请先登录!");
            }

            accountService.deleteAllFavorite(currentUser.getUserId());

            return new BaseResponse(StatusCode.Success);
        }catch (Exception e){
            log.error(e.toString());
            return new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
    }

}
