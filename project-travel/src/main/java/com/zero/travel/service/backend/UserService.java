package com.zero.travel.service.backend;

import com.zero.travel.mapper.UserMapper;
import com.zero.travel.pojo.dto.UserDTO;
import com.zero.travel.pojo.entity.User;
import com.zero.travel.service.common.MailService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private MailService mailService;

    @Autowired
    private Environment env;

    /**
     * 客户信息展示
     * @return
     */
    public List<User> findAll() {
        List<User> userList = userMapper.selectFindAll();

        return userList;
    }

    /**
     * 多条件查询
     * @param userDTO
     * @return
     */
    public List<User> findByParam(UserDTO userDTO){
        if (ObjectUtils.isNotEmpty(userDTO)){
            User userParam = new User();
            BeanUtils.copyProperties(userDTO,userParam);
            List<User> userList = userMapper.selectByParam(userParam);

            return userList;
        }
        return null;
    }

    /**
     * 修改用户状态
     * @param userId
     */
    public void modifyStatus(Integer userId) throws Exception{
        User currUser = userMapper.selectByPrimaryKey(userId);
        if (ObjectUtils.isEmpty(currUser)){
            throw new RuntimeException("客户不存在");
        }
        Integer status = 1;
        if (currUser.getStatus() == 1){
            status = 0;
        }
        if (currUser.getStatus() == 0){
            status = 1;
        }
        int row = userMapper.updateStatus(status,userId);
        if (row != 1){
            throw new RuntimeException("修改失败");
        }
    }

    /**
     * 找回密码
     * @param userId
     */
    public void retrievePassword(Integer userId) throws Exception {
        User user = userMapper.selectByPrimaryKey(userId);
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("name",user.getName());
        paramMap.put("password",user.getPassword());

        String mailTemplate = mailService.renderTemplate(env.getProperty("mail.template.file.location"), paramMap);
        String subject = "密码找回";

        mailService.sendHtmlMail(subject,mailTemplate,user.getEmail());
    }
}
