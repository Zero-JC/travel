package com.zero.travel.common.interceptor;

import com.zero.travel.common.enums.SystemConstant;
import com.zero.travel.common.exception.NoPermissionException;
import com.zero.travel.common.exception.NotLoginException;
import com.zero.travel.pojo.entity.SysUser;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 后台管理系统 登录拦截
 * @author LJC
 * @version 1.0
 * @date 2021/3/19 23:21
 */
@Component
public class BackendLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final HttpSession session = request.getSession();
        final SysUser currentUser = (SysUser) session.getAttribute("currentSysUser");
        if (currentUser == null){

            throw new NotLoginException("用户未登录");
        }

        final Integer roleId = currentUser.getRoleId();

        final String requestUri = request.getRequestURI();

        //系统用户管理
        if (requestUri.contains(SystemConstant.SYS_USER)){
            if (!roleId.equals(SystemConstant.ROLE_SYS_USER)){
                throw new NoPermissionException("当前请求需要超级管理员权限");
            }
        }
        //客户管理 商家管理 需要网站管理员权限或超级管理员权限
        if (requestUri.contains(SystemConstant.USER) || requestUri.contains(SystemConstant.SELLER)){
            if (!roleId.equals(SystemConstant.ROLE_USER) && !roleId.equals(SystemConstant.ROLE_SYS_USER)){
                throw new NoPermissionException("当前请求需要网站管理员权限或超级管理员权限");
            }
        }
        //旅游线路管理 商家角色只有部分权限
        if (requestUri.contains(SystemConstant.ROUTE)){
            if (!roleId.equals(SystemConstant.ROLE_SELLER) && !roleId.equals(SystemConstant.ROLE_USER) && !roleId.equals(SystemConstant.ROLE_SYS_USER)){
                throw new NoPermissionException("当前请求需要商家权限、网站管理员权限或超级管理员权限");
            }
        }

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
