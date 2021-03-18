package com.zero.travel.controller.backend;

import com.zero.travel.common.enums.StatusCode;
import com.zero.travel.common.response.BaseResponse;
import com.zero.travel.common.util.ValidateUtils;
import com.zero.travel.controller.CommonController;
import com.zero.travel.pojo.entity.Seller;
import com.zero.travel.pojo.vo.RouteVO;
import com.zero.travel.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * @author LJC
 * @version 1.0
 * @date 2021/3/17 14:46
 */
@Controller
@RequestMapping("/backend/route")
public class RouteController extends CommonController {

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

    @RequestMapping(value = "/add",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public BaseResponse add(RouteVO routeVO){
        try {

            log.info(routeVO.toString());
            return new BaseResponse(StatusCode.Success);
        }catch (Exception e){
            return new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
    }
}
