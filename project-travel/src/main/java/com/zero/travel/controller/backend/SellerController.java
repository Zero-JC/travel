package com.zero.travel.controller.backend;

import com.zero.travel.common.enums.StatusCode;
import com.zero.travel.common.response.BaseResponse;
import com.zero.travel.common.util.ValidateUtils;
import com.zero.travel.controller.CommonController;
import com.zero.travel.mapper.SellerMapper;
import com.zero.travel.pojo.dto.SellerDTO;
import com.zero.travel.pojo.entity.Seller;
import com.zero.travel.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    @RequestMapping("/findAll")
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
     * @param result
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse add(@Validated SellerDTO sellerDTO, BindingResult result){
        try {
            if (result.hasErrors()){
                String checkResult = ValidateUtils.checkResult(result);

                return new BaseResponse(StatusCode.Fail.getCode(),checkResult);
            }
            log.info(sellerDTO.toString());
            sellerService.add(sellerDTO);
            return new BaseResponse(StatusCode.Success);
        } catch (Exception e) {
            return new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
    }

    /**
     * 修改
     * @param sellerDTO
     * @return
     */
    @RequestMapping(value = "/modify",method = RequestMethod.POST)
    public String modify(SellerDTO sellerDTO,Model model){
        try {
            sellerService.modify(sellerDTO);
            model.addAttribute("successMsg","修改成功");
            return "forward:findAll";
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("errorMsg","修改失败");
            return "forward:findAll";
        }
    }

    /**
     * 删除用户
     * @param sellerId
     * @return
     */
    @RequestMapping("/remove")
    @ResponseBody
    public BaseResponse remove(Integer sellerId){
        try{
            sellerService.remove(sellerId);
            return new BaseResponse(StatusCode.Success);
        }catch (Exception e){
            return new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
    }
}
