package com.zero.travel.controller.backend;

import com.zero.travel.common.enums.StatusCode;
import com.zero.travel.common.response.BaseResponse;
import com.zero.travel.controller.CommonController;
import com.zero.travel.pojo.dto.SellerDTO;
import com.zero.travel.pojo.entity.Seller;
import com.zero.travel.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 商家
 * @author LJC
 * @version 1.0
 * @date 2021/3/3 22:20
 */
@Controller
@RequestMapping("/backend/seller")
public class SellerController extends CommonController {

    @Autowired
    private SellerService sellerService;

    /**
     * 查询所有商家信息
     * @param model
     * @return
     */
    @RequestMapping("/findAll")
    public String findAll(Model model){
        List<Seller> sellerList = sellerService.findAll();

        model.addAttribute("sellers",sellerList);
        return "backend/sellerManager";
    }

    /**
     * 新增
     * @param sellerDTO
     * @return
     */
    @RequestMapping("/add")
    public @ResponseBody BaseResponse<Object> add(SellerDTO sellerDTO){
        try {
            log.info(sellerDTO.toString());
            sellerService.add(sellerDTO);

            return new BaseResponse<>(StatusCode.Success);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse<>(StatusCode.Fail.getCode(),e.getMessage());
        }
    }
}
