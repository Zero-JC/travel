package com.zero.travel.config;

import com.sun.xml.internal.ws.client.HandlerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author LJC
 * @version 1.0
 * @date 2021/2/26 22:42
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 配置视图跳转
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/showLogin").setViewName("/backend/login");
        registry.addViewController("/welcome").setViewName("welcome");
        registry.addViewController("/index").setViewName("index");
    }
}
