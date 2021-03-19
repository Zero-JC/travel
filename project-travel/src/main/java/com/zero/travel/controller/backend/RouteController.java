package com.zero.travel.controller.backend;


import com.zero.travel.controller.CommonController;
import com.zero.travel.pojo.dto.RouteDTO;
import com.zero.travel.pojo.entity.Route;
import com.zero.travel.pojo.entity.Seller;
import com.zero.travel.service.backend.RouteService;
import com.zero.travel.service.backend.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
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
    @RequestMapping("/findAll")
    public String findAll(Model model){
        List<Route> routeList = routeService.findAll();

        model.addAttribute("routeList",routeList);
        return "backend/routeManager";
    }

    /**
     * 新增旅游线路
     * @param request
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String add(MultipartHttpServletRequest request, Model model){
        try {

            final RouteDTO routeDTO = packageParam(request);

            routeService.add(routeDTO);

            model.addAttribute("msg","操作成功");
            return "backend/routeManager";
        }catch (Exception e){
            model.addAttribute("msg","操作失败");
            return "backend/routeManager";
        }
    }

    @RequestMapping(value = "modify",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String modify(MultipartHttpServletRequest request,Model model){


        return "backend/routeManager";
    }

    /**
     * 获取线路图片
     * @param path
     * @param response
     */
    @RequestMapping("/getImage")
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

        final String routeName = request.getParameter("routeName");
        Integer price = Integer.parseInt(request.getParameter("price"));
        final String routeIntroduce = request.getParameter("routeIntroduce");
        final String strategy = request.getParameter("strategy");
        final MultipartFile file = request.getFile("fileName");
        Integer sellerId = Integer.parseInt(request.getParameter("sellerId"));

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
