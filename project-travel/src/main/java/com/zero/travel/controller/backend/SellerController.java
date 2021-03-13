package com.zero.travel.controller.backend;

import com.zero.travel.common.enums.StatusCode;
import com.zero.travel.common.response.BaseResponse;
import com.zero.travel.controller.CommonController;
import com.zero.travel.mapper.SellerMapper;
import com.zero.travel.pojo.dto.SellerDTO;
import com.zero.travel.pojo.entity.Seller;
import com.zero.travel.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private SellerMapper sellerMapper;

    /**
     * 查询所有商家信息
     * @param model
     * @return
     */
    @GetMapping("/findAll")
    public String findAll(Model model){
        List<Seller> sellerList = sellerService.findAll();

        model.addAttribute("sellers",sellerList);
        return "backend/sellerManager";
    }

    /**
     * 回显信息
     * @param sellerId
     * @return
     */
    @PostMapping("/findById")
    public @ResponseBody Seller findById(@RequestParam Integer sellerId){
        Seller seller = sellerMapper.selectByPrimaryKey(sellerId);

        return seller;
    }

    /**
     * 新增
     * @param sellerDTO
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(SellerDTO sellerDTO,Model model){
        try {
            log.info(sellerDTO.toString());
            sellerService.add(sellerDTO);

            model.addAttribute("successMsg","添加成功");
        } catch (Exception e) {
            model.addAttribute("errorMsg","添加失败");
            e.printStackTrace();
        }

        return "redirect:findAll";
    }

    /**
     * 修改
     * @param sellerDTO
     * @return
     */
    @RequestMapping(value = "/modify",method = RequestMethod.POST)
    public @ResponseBody BaseResponse modify(SellerDTO sellerDTO){
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            sellerService.modify(sellerDTO);
            response.setMsg("修改成功!");
        } catch (Exception e) {
            log.error(e.getMessage());
            response.setCode(StatusCode.Fail.getCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }
}
