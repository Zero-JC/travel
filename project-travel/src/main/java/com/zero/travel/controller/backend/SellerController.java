package com.zero.travel.controller.backend;

import com.zero.travel.controller.CommonController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author LJC
 * @version 1.0
 * @date 2021/3/3 22:20
 */
@Controller
@RequestMapping("backend/seller")
public class SellerController extends CommonController {

    @RequestMapping("findAll")
    public String findAll(){

        return "/backend/sellerManager";
    }
}
