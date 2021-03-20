package com.zero.travel.controller.front;

import com.zero.travel.controller.CommonController;
import com.zero.travel.pojo.entity.Route;
import com.zero.travel.pojo.entity.Seller;
import com.zero.travel.service.backend.RouteService;
import com.zero.travel.service.backend.SellerService;
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

    /**
     * 门户网站主页
     * 展示旅游线路信息-查询所有线路信息
     * @return
     */
    @RequestMapping("/index")
    public String main(Model model){

        List<Route> routeList = routeService.findAll();
        model.addAttribute("routeList",routeList);

        return "front/main";
    }

    @ModelAttribute("sellers")
    public List<Seller> sellerList(){

        return sellerService.findAll();
    }
}
