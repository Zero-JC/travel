package com.zero.travel.controller.front;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zero.travel.common.enums.SystemConstant;
import com.zero.travel.common.util.SystemUtils;
import com.zero.travel.controller.CommonController;
import com.zero.travel.pojo.entity.Route;
import com.zero.travel.pojo.entity.Seller;
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
    private RouteService routeService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private FrontRouteService frontRouteService;

    @ModelAttribute("sellers")
    public List<Seller> sellerList(){

        return sellerService.findAll();
    }


    /**
     * 门户网站主页
     * 展示旅游线路信息-查询所有线路信息
     * @return
     */
    /*@RequestMapping("/index")
    public String main(Model model){

        List<Route> routeList = routeService.findAll();
        model.addAttribute("routeList",routeList);

        return "front/main";
    }*/

    @RequestMapping("/search")
    public String search(Integer pageNum, RouteSearchQuery routeSearchQuery,Model model){
        try {
            if (ObjectUtils.isEmpty(pageNum)){
                pageNum = 1;
            }
            log.info(routeSearchQuery.toString());

            List<Route> routeList = null;
            if (SystemUtils.isAllFieldNull(routeSearchQuery)){
                PageHelper.startPage(pageNum, 4);
                routeList = frontRouteService.search(null);
            }else {
                PageHelper.startPage(pageNum, 4);
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

}
