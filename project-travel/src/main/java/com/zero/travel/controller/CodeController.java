package com.zero.travel.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author LJC
 * @version 1.0
 * @date 2020/12/11 20:15
 */
@Controller
@RequestMapping("/code")
public class CodeController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 生成验证码图片
     */
    @RequestMapping("/image")
    public void verificationCode( HttpServletResponse response,HttpServletRequest request) throws IOException {

        ///
        //服务器通知浏览器不要缓存
        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","no-cache");
        response.setHeader("expires","0");

        //在内存中创建一个长80，宽30的图片，默认黑色背景
        //参数一：长
        //参数二：宽
        //参数三：颜色
        int width = 80;
        int height = 30;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        //获取画笔
        Graphics g = image.getGraphics();
        //设置画笔颜色为灰色
        g.setColor(Color.GRAY);
        //填充图片
        g.fillRect(0,0, width,height);

        //产生4个随机验证码，12Ey
        String checkCode = getCheckCode();

        //将验证码放入session中
        final HttpSession session = request.getSession();
        session.setAttribute("checkCode",checkCode);

        //将验证码放入redis中
        ///stringRedisTemplate.opsForValue().set("checkCode",checkCode);

        //设置画笔颜色为黄色
        g.setColor(Color.YELLOW);
        //设置字体的小大
        g.setFont(new Font("黑体", Font.BOLD,24));
        //向图片上写入验证码
        g.drawString(checkCode,15,25);

        //设置干扰线
        for (int i = 0;i < 6; i++){
            //干扰线起点、终点
            int xStart = (int)(Math.random()*80);
            int yStart = (int)(Math.random()*25);
            int xEnd = (int)(Math.random()*80);
            int yEnd = (int)(Math.random()*25);

            g.setColor(Color.black);
            g.drawLine(xStart,yStart,xEnd,yEnd);
        }

        //将内存中的图片输出到浏览器
        //参数一：图片对象
        //参数二：图片的格式，如PNG,JPG,GIF
        //参数三：图片输出到哪里去
        ImageIO.write(image,"PNG",response.getOutputStream());
    }


    /**
     * 验证码校验
     * @param code
     * @return
     */
    @RequestMapping("/checkCode")
    @ResponseBody
    public Map<String,Object> checkCode(String code,HttpServletRequest request){
        Map<String,Object> map = new HashMap<>(5);

        ///String checkCode = stringRedisTemplate.opsForValue().get("checkCode");

        final HttpSession session = request.getSession();
        final String checkCode = (String) session.getAttribute("checkCode");
        if (code.equalsIgnoreCase(checkCode)){
            map.put("valid",true);
        }else {
            map.put("valid",false);
        }
        return map;
    }



    /**
     * 产生4位随机字符串
     */
    private String getCheckCode() {
        String base = "0123456789ABCDEFGabcdefg";
        int size = base.length();
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i=1;i<=4;i++){
            //产生0到size-1的随机值
            int index = r.nextInt(size);
            //在base字符串中获取下标为index的字符
            char c = base.charAt(index);
            //将c放入到StringBuffer中去
            sb.append(c);
        }
        return sb.toString();
    }
}
