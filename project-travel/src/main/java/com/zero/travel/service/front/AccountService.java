package com.zero.travel.service.front;

import com.zero.travel.mapper.FavoriteMapper;
import com.zero.travel.mapper.RouteFavoriteMapper;
import com.zero.travel.mapper.UserMapper;
import com.zero.travel.pojo.dto.UserDTO;
import com.zero.travel.pojo.entity.Favorite;
import com.zero.travel.pojo.entity.FavoriteKey;
import com.zero.travel.pojo.entity.RouteFavorite;
import com.zero.travel.pojo.entity.User;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    private FavoriteMapper favoriteMapper;

    @Autowired
    private RouteFavoriteMapper routeFavoriteMapper;

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

    /**
     * 注册
     * @param userDTO
     */
    public void register(UserDTO userDTO) throws Exception{
        final User user = userMapper.selectByUsername(userDTO.getUsername());
        if (user != null){
            throw new Exception("用户名已存在");
        }
        User userParam = new User();
        BeanUtils.copyProperties(userDTO,userParam);

        final int row = userMapper.insertSelective(userParam);
        if (row != 1){
            throw new Exception("注册失败");
        }
    }

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    public void modifyPassword(Integer userId, String newPassword) throws Exception {
        if (ObjectUtils.isEmpty(userId)){
            throw new Exception("用户id为空");
        }
        if (ObjectUtils.isEmpty(newPassword) && "".equals(newPassword)){
            throw new Exception("新密码为空");
        }
        User userParam = new User();
        userParam.setUserId(userId);
        userParam.setPassword(newPassword);

        final int row = userMapper.updateByPrimaryKeySelective(userParam);
        if (row != 1){
            throw new Exception("修改密码失败");
        }
    }

    /**
     * 修改基本信息
     * @param userDTO
     * @throws Exception
     */
    public User modifyInfo(UserDTO userDTO) throws Exception{
        User userParam = new User();
        BeanUtils.copyProperties(userDTO,userParam);

        final User user = userMapper.selectByUsername(userParam.getUsername());
        if (user == null){
            throw new Exception("当前用户不存在");
        }
        userParam.setUserId(user.getUserId());

        final int row = userMapper.updateByPrimaryKeySelective(userParam);

        if (row != 1){
            throw new Exception("修改用户资料失败");
        }

        //返回用户对象，用于更新Session
        return userMapper.selectByPrimaryKey(user.getUserId());
    }

    /**
     * 用户收藏旅游线路
     * @param favoriteKey
     */
    public void addFavorite(FavoriteKey favoriteKey) throws Exception{

        //TODO:判断是否有收藏记录
        final Favorite favorite = favoriteMapper.selectByPrimaryKey(favoriteKey);
        if (favorite != null){
            throw new Exception("此线路已收藏");
        }
        //TODO: 插入收藏记录
        Favorite favoriteParam = new Favorite();
        BeanUtils.copyProperties(favoriteKey,favoriteParam);
        final int row = favoriteMapper.insertSelective(favoriteParam);
        if (row != 1){
            throw new Exception("插入收藏记录失败");
        }
        //TODO: 更新线路收藏表
        final RouteFavorite routeFavorite = routeFavoriteMapper.selectByPrimaryKey(favoriteKey.getRouteId());
        if (routeFavorite == null){
            throw new Exception("当前线路不在线路收藏表中");
        }
        routeFavorite.setCount(routeFavorite.getCount()+1);

        final int num = routeFavoriteMapper.updateByPrimaryKey(routeFavorite);
        if (num != 1){
            throw new Exception("更新线路收藏表失败");
        }
    }

    /**
     * 删除收藏记录
     * @param routeId
     * @param userId
     */
    public void deleteFavorite(Integer routeId, Integer userId) throws Exception{
        //TODO: 删除用户收藏表记录
        final int rowOne = favoriteMapper.deleteByPrimaryKey(new FavoriteKey(userId, routeId));
        if (rowOne != 1){
            throw new Exception("删除用户收藏记录失败");
        }
        //TODO: 修改线路收藏表记录
        final RouteFavorite routeFavorite = routeFavoriteMapper.selectByPrimaryKey(routeId);
        routeFavorite.setCount(routeFavorite.getCount()-1);

        final int roeTow = routeFavoriteMapper.updateByPrimaryKey(routeFavorite);
        if (roeTow != 1){
            throw new Exception("修改线路收藏记录失败");
        }

    }
}
