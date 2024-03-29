package com.zero.travel.controller.backend;


import com.zero.travel.common.enums.StatusCode;
import com.zero.travel.common.response.BaseResponse;
import com.zero.travel.common.util.SystemUtils;
import com.zero.travel.controller.CommonController;
import com.zero.travel.pojo.dto.RouteDTO;
import com.zero.travel.pojo.entity.Route;
import com.zero.travel.pojo.entity.Seller;
import com.zero.travel.pojo.vo.RouteVO;
import com.zero.travel.service.backend.RouteService;
import com.zero.travel.service.backend.SellerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author LJC
 * @version 1.0
 * @date 2021/3/17 14:46
 */
@Controller
@RequestMapping("/backend/route")
@Api(tags = "旅游线路管理接口")
public class RouteController extends CommonController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private RouteService routeService;


    @ModelAttribute("sellerList")
    public List<Seller> sellerList(){
        List<Seller> sellerList = sellerService.findAll();

        return sellerList;
    }

    /**
     * 查询所有
     * @param model
     * @return
     */
    @ApiOperation("获取旅游线路列表")
    @GetMapping("/findAll")
    public String findAll(Model model){
        List<Route> routeList = routeService.findAll();


        model.addAttribute("routeList",routeList);
        RouteVO routeVO = new RouteVO();
        model.addAttribute("searchParam",routeVO);
        return "backend/routeManager";
    }

    /**
     * 新增旅游线路
     * @param request
     * @return
     */
    @ApiOperation("add")
    @RequestMapping(value = "/add",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String add(MultipartHttpServletRequest request, Model model){
        try {

            final RouteDTO routeDTO = packageParam(request);

            routeService.add(routeDTO);

            model.addAttribute("msg","操作成功");

            return "forward:findAll";
        }catch (Exception e){
            model.addAttribute("msg","操作失败");

            return "forward:findAll";
        }
    }

    /**
     * 修改线路信息
     * @param request
     * @param model
     * @return
     */
    @ApiOperation("modify")
    @RequestMapping(value = "modify",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String modify(MultipartHttpServletRequest request,Model model){
        try {
            final RouteDTO routeDTO = packageParam(request);

            log.info(routeDTO.toString());
            log.info("Filename:"+routeDTO.getImageFile().getOriginalFilename());

            routeService.modify(routeDTO);

            model.addAttribute("msg","操作成功");
            return "redirect:findAll";
        }catch (Exception e){
            model.addAttribute("msg","操作失败");
            return "forward:findAll";
        }
    }

    /**
     * 删除
     * @param routeId
     * @return
     */
    @ApiOperation("del")
    @RequestMapping(value = "/remove",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse remove(@RequestParam Integer routeId){
        try{
            routeService.remove(routeId);
            return new BaseResponse(StatusCode.Success);
        }catch (Exception e){
            log.error(e.getMessage());
            log.error(e.toString());
            return new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
    }

    /**
     * 条件查询
     * @param routeVO
     * @param model
     * @return
     */
    @ApiOperation("条件搜索")
    @PostMapping("/search")
    public String search(RouteVO routeVO,Model model){
        try {
            List<Route> routeList = null;
            RouteDTO routeDTO = new RouteDTO();
            BeanUtils.copyProperties(routeVO,routeDTO);
            if (routeVO.getSellerId() == -1){
                routeDTO.setSellerId(null);
            }
            if ("".equals(routeVO.getRouteName())){
                routeDTO.setRouteName(null);
            }
            if (SystemUtils.isAllFieldNull(routeDTO)){
                routeList = routeService.findAll();
            }else {
                routeList = routeService.search(routeDTO);
            }

            model.addAttribute("routeList",routeList);
            model.addAttribute("searchParam",routeVO);
            return "backend/routeManager";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg",e.toString());
            return "backend/routeManager";
        }
    }

    /**
     * 获取线路图片
     * @deprecated 此接口已弃用
     * @param path
     * @param response
     */
    @RequestMapping("/getImage")
    @ApiIgnore
    public void getImage(String path, HttpServletResponse response){
        try {
            final ServletOutputStream outputStream = response.getOutputStream();
            log.info(path);
            routeService.getImage(path,outputStream);
        }catch (Exception e){
            log.error("加载图片失败:{}",e.toString());
        }
    }

    /**
     * 封装参数
     * @param request
     * @return
     */
    public static RouteDTO packageParam(MultipartHttpServletRequest request){
        RouteDTO routeDTO = new RouteDTO();

        final String routeId = request.getParameter("routeId");

        final String routeName = request.getParameter("routeName");
        Double price = (double)(Integer.parseInt(request.getParameter("price")));
        final String routeIntroduce = request.getParameter("routeIntroduce");
        final String strategy = request.getParameter("strategy");
        final MultipartFile file = request.getFile("fileName");
        Integer sellerId = Integer.parseInt(request.getParameter("sellerId"));

        if (routeId != null && !"".equals(routeId)){
            routeDTO.setRouteId(Integer.parseInt(routeId));
        }
        routeDTO.setRouteName(routeName);
        routeDTO.setPrice(price);
        routeDTO.setRouteIntroduce(routeIntroduce);
        routeDTO.setStrategy(strategy);
        routeDTO.setImageFile(file);
        routeDTO.setSellerId(sellerId);
        ///BeanUtils.populate();

        return routeDTO;
    }
}
