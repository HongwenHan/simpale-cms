/*
 * @(#)UserServiceImpl.java 2018年4月23日上午11:31:26
 * news
 * Copyright 2018 Thuisoft, Inc. All rights reserved.
 * THUNISOFT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.news.service.admin.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.dao.admin.IUserRepositiry;
import com.news.entity.User;
import com.news.service.admin.IUserService;

// TODO: Auto-generated Javadoc
/**
 * UserServiceImpl.
 *
 * @author hhong
 * @version 1.0
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    /** The user dao. */
    @Autowired
    private IUserRepositiry userDao;

    /**
     * (non-Javadoc).
     *
     * @param session the session
     * @param username the username
     * @param password the password
     * @return the boolean
     * @see com.news.service.admin.IUserService#userValidate(javax.servlet.http.HttpSession,
     *      java.lang.String, java.lang.String)
     */
    @Override
    public Boolean userValidate(HttpSession session, String username,
            String password) {
        User user = findByUserName(username);
        String md5Pass = DigestUtils.md5Hex(password);
        if (user != null && user.getPassword().equals(md5Pass)) {
            session.setAttribute("user", user);
            return true;
        } else {
            return false;
        }

    }

    /**
     * (non-Javadoc).
     *
     * @param username the username
     * @return the user
     * @see com.news.service.admin.IUserService#findByUserName(java.lang.String)
     */
    @Override
    public User findByUserName(String username) {
        if (StringUtils.isNotBlank(username)) {
            User user = userDao.findByUsername(username);
            return user;
        }
        return null;
    }

    /**
     * (non-Javadoc).
     *
     * @return the list
     * @see com.news.service.admin.IUserService#findUsers()
     */
    @Override
    public List<User> findUsers() {
        return userDao.findAll();
    }

    /**
     * (non-Javadoc).
     *
     * @param username the username
     * @return the list
     * @see com.news.service.admin.IUserService#findUsersByUsername(java.lang.String)
     */
    @Override
    public List<User> findUsersByUsername(String username) {
        if(StringUtils.isNotBlank(username)){
            return userDao.findUsersByUsername(username);
        }else{
            return userDao.findAll();
        }
    }

    /**
     *  (non-Javadoc).
     *
     * @param userId the user id
     * @return the user
     * @see com.news.service.admin.IUserService#findByUserId(java.lang.Integer)
     */
    @Override
    public User findByUserId(Integer userId) {
        return userDao.findById(userId);
    }

    /**
     *  (non-Javadoc).
     *
     * @param user the user
     * @see com.news.service.admin.IUserService#deleteUser(com.news.entity.User)
     */
    @Override
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    /**
     *  (non-Javadoc).
     *
     * @param user the user
     * @return the user
     * @see com.news.service.admin.IUserService#saveUser(com.news.entity.User)
     */
    @Override
    public User saveUser(User user) {
        return userDao.save(user);
    }

}
