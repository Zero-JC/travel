package com.zero.travel.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LJC
 * @version 1.0
 * @date 2021/3/22 13:26
 */
//@Configuration
public class OssConfig {

    @Value("${endpoint}")
    private String endpoint;

    @Value("${accessKeyId}")
    private String accessKeyId;

    @Value("${accessKeySecret}")
    private String accessKeySecret;

    @Bean(name = "oss")
    public OSS oss(){
        return new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
    }

}
