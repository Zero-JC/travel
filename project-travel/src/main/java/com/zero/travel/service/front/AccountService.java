package com.zero.travel.service.front;

import com.zero.travel.mapper.UserMapper;
import com.zero.travel.pojo.dto.UserDTO;
import com.zero.travel.pojo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * 用户业务处理
 * @author LJC
 * @version 1.0
 * @date 2021/3/20 14:29
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class AccountService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 登录业务
     * @param userDTO
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public User login(UserDTO userDTO) throws Exception{
        User user = userMapper.selectByUsername(userDTO.getUsername());
        if (user == null){
            throw new Exception("用户不存在");
        }

        if (user.getStatus() == 0){
            throw new Exception("账户已禁用,请联系管理员");
        }

        if (!user.getPassword().equals(userDTO.getPassword())){
            throw new Exception("密码不正确");
        }

        return user;
    }

}
