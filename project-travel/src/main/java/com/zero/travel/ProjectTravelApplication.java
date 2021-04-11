package com.zero.travel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author LJC
 */
@SpringBootApplication
@EnableSwagger2
public class ProjectTravelApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectTravelApplication.class, args);
    }
}
