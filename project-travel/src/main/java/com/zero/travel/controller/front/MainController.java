package com.zero.travel.controller.front;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zero.travel.common.enums.StatusCode;
import com.zero.travel.common.enums.SystemConstant;
import com.zero.travel.common.response.BaseResponse;
import com.zero.travel.common.util.SystemUtils;
import com.zero.travel.controller.CommonController;
import com.zero.travel.mapper.SellerMapper;
import com.zero.travel.pojo.entity.Route;
import com.zero.travel.pojo.entity.Seller;
import com.zero.travel.pojo.entity.User;
import com.zero.travel.pojo.vo.RouteSearchQuery;
import com.zero.travel.service.backend.RouteService;
import com.zero.travel.service.backend.SellerService;
import com.zero.travel.service.front.FrontRouteService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author LJC
 * @version 1.0
 * @date 2021/3/20 11:52
 */
@Controller
@RequestMapping("/front")
public class MainController extends CommonController {

    @Autowired
    private SellerMapper sellerMapper;

    @Autowired
    private SellerService sellerService;


    @Autowired
    private FrontRouteService frontRouteService;

    @ModelAttribute("sellers")
    public List<Seller> sellerList(){

        return sellerService.findAll();
    }

    /**
     * 主页
     * @param pageNum
     * @param routeSearchQuery
     * @param model
     * @return
     */
    @RequestMapping("/search")
    public String search(Integer pageNum, RouteSearchQuery routeSearchQuery,Model model){
        try {
            if (ObjectUtils.isEmpty(pageNum)){
                pageNum = 1;
            }
            log.info(routeSearchQuery.toString());

            List<Route> routeList = null;
            if (SystemUtils.isAllFieldNull(routeSearchQuery)){
                PageHelper.startPage(pageNum, SystemConstant.PAGE_SIZE_FRONT);
                routeList = frontRouteService.search(null);
            }else {
                PageHelper.startPage(pageNum, SystemConstant.PAGE_SIZE_FRONT);
                routeList = frontRouteService.search(routeSearchQuery);
            }
            PageInfo<Route> pageInfo = new PageInfo<>(routeList);

            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("searchQuery",routeSearchQuery);
            return "front/main";
        } catch (Exception e) {
            e.printStackTrace();
            return "front/main";
        }
    }

    /**
     * 会员中心
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/info")
    public String userInfo(Model model, HttpSession session){
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null){

            model.addAttribute("msg","请先登录！");
            return "forward:/front/search";
        }

        model.addAttribute("currUser",currentUser);

        return "front/userInfo";
    }

    /**
     * 我的收藏
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/myFavorite")
    public String myFavorite(Model model,HttpSession session){
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null){

            model.addAttribute("msg","请先登录！");
            return "forward:/front/search";
        }

        model.addAttribute("currUser",currentUser);

        return "front/myFavorite";
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

}
