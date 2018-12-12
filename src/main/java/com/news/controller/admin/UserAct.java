/*
 * @(#)UserACt.java 2018年5月30日下午5:13:01
 * news
 * Copyright 2018 Thuisoft, Inc. All rights reserved.
 * THUNISOFT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.news.controller.admin;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.news.entity.User;
import com.news.service.admin.IUserService;
import com.news.util.RequestUtils;

/**
 * UserACt
 * 
 * @author hanhw
 * @version 1.0
 *
 */
@Controller
public class UserAct {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "user")
    public String userList(HttpServletRequest request,
            Map<String, Object> map) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String username = RequestUtils.getQueryParam(request, "searchname");
        List<User> users = userService.findUsersByUsername(username);
        map.put("users", users);
        map.put("user", user);
        map.put("isUser", true);
        return "admin/user";
    }

    @RequestMapping(value = "addUser")
    public String addUser(Map<String, Object> map) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        map.put("user", user);
        return "admin/addUser";
    }

    @RequestMapping(value = "saveUser")
    public String saveUser(HttpServletRequest request,
            Map<String, Object> map) {
        User user = getUser(request);
        user = userService.saveUser(user);
        return "redirect:user";
    }

    /**
     * @param request
     * @return
     */
    private User getUser(HttpServletRequest request) {
        User user = new User();
        String userId = RequestUtils.getQueryParam(request, "userId");
        if (StringUtils.isNotBlank(userId)) {
            user.setId(Integer.valueOf(userId));
        }
        String username = RequestUtils.getQueryParam(request, "username");
        if (StringUtils.isNotBlank(username)) {
            user.setUsername(username);
        }
        String realname = RequestUtils.getQueryParam(request, "realname");
        if (StringUtils.isNotBlank(realname)) {
            user.setRealname(realname);
        }
        String password = RequestUtils.getQueryParam(request, "password");
        if (StringUtils.isNotBlank(password)) {
            user.setPassword(getMD5(password));
            user.setSalt(getMD5(password));
        }
        String isAdmin = RequestUtils.getQueryParam(request, "isAdmin");
        if (StringUtils.isNotBlank(isAdmin)) {
            user.setIsAdmin(Boolean.valueOf(isAdmin));
        }
        String state = RequestUtils.getQueryParam(request, "state");
        if (StringUtils.isNotBlank(state)) {
            user.setState(Boolean.valueOf(isAdmin));
        }
        return user;
    }

    @RequestMapping(value = "deleteUser")
    public String deleteUser(HttpServletRequest request,
            Map<String, Object> map) {
        User syuser = (User) SecurityUtils.getSubject().getPrincipal();
        String userId = RequestUtils.getQueryParam(request, "userId");
        User user = userService.findByUserId(Integer.valueOf(userId));
        userService.deleteUser(user);
        List<User> users = userService.findUsers();
        map.put("users", users);
        map.put("user", syuser);
        return "redirect:user";
    }

    @RequestMapping(value = "editUser")
    public String editUser(HttpServletRequest request,
            Map<String, Object> map) {
        User syuser = (User) SecurityUtils.getSubject().getPrincipal();
        String userId = RequestUtils.getQueryParam(request, "userId");
        User user = userService.findByUserId(Integer.valueOf(userId));
        map.put("user", syuser);
        map.put("editUser", user);
        return "admin/editUser";
    }
    
    public static String getMD5(String str) {  
        try {  
            // 生成一个MD5加密计算摘要  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            // 计算md5函数  
            md.update(str.getBytes());  
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符  
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值  
            return new BigInteger(1, md.digest()).toString(16);  
        } catch (Exception e) {  
           e.printStackTrace();  
           return null;  
        }  
    }  

}
