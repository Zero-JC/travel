package com.zero.travel.web;

import com.zero.travel.pojo.dto.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LJC
 * @version 1.0
 * @date 2021/2/25 12:06
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/response")
    public List<User> test(){
        List<User> list = new ArrayList<>();
        list.add(new User("Jack",19));
        list.add(new User("Tom",25));
        list.add(new User("Alice",21));

        return list;
    }
}
