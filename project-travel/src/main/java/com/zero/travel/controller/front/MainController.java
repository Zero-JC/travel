package com.zero.travel.controller.front;

import com.zero.travel.controller.CommonController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @author LJC
 * @version 1.0
 * @date 2021/3/20 11:52
 */
@Controller
@RequestMapping("/front")
public class MainController extends CommonController {

    /**
     * 门户网站主页
     * 展示旅游线路信息
     * @return
     */
    @RequestMapping("/index")
    public String main(){

        return "front/main";
    }

}
