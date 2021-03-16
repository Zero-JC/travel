package com.zero.travel.service;

import com.zero.travel.mapper.UserMapper;
import com.zero.travel.pojo.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 客户业务处理
 * @author LJC
 * @version 1.0
 * @date 2021/3/16 18:00
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 客户信息展示
     * @return
     */
    public List<User> findAll() {
        List<User> userList = userMapper.selectFindAll();

        return userList;
    }
}
