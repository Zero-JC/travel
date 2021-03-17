package com.zero.travel.controller.backend;

import com.zero.travel.pojo.entity.Seller;
import com.zero.travel.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author LJC
 * @version 1.0
 * @date 2021/3/17 14:46
 */
@Controller
@RequestMapping("/backend/route")
public class RouteController {

    @Autowired
    private SellerService sellerService;

    @ModelAttribute("sellerList")
    public List<Seller> sellerList(){
        List<Seller> sellerList = sellerService.findAll();

        return sellerList;
    }

    @RequestMapping("/findAll")
    public String findAll(){

        return "backend/routeManager";
    }

}
