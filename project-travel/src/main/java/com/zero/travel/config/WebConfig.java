package com.zero.travel.config;

import com.zero.travel.common.interceptor.BackendLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LJC
 * @version 1.0
 * @date 2021/2/26 22:42
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private BackendLoginInterceptor backendLoginInterceptor;

    /**
     * 配置视图跳转
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/showLogin").setViewName("backend/login");
        registry.addViewController("/welcome").setViewName("welcome");
        registry.addViewController("/index").setViewName("index");
    }

    /**
     * 文件解析器 使用CommonsMultipartFile类型接受 file文件是需要配置文件解析
     * @return
     */
    /*@Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(10000000);
        return commonsMultipartResolver;
    }*/

    /**
     * 自定义拦截器
     * @return
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        List<String> excludePath = new ArrayList<>();
        excludePath.add("/backend/sysUser/login");
        excludePath.add("/backend/sysUser/logout");
        excludePath.add("/backend/sysUser/showLogin");

        registry.addInterceptor(backendLoginInterceptor).addPathPatterns("/backend/**")
                .excludePathPatterns(excludePath);
    }
}
