/*
 * @(#)IUserSrvice.java 2018年4月22日下午2:59:52
 * news
 * Copyright 2018 Thuisoft, Inc. All rights reserved.
 * THUNISOFT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.news.service.admin;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.news.entity.User;

// TODO: Auto-generated Javadoc
/**
 * IUserSrvice.
 *
 * @author hhong
 * @version 1.0
 */
public interface IUserService {

    /**
     * User validate.
     *
     * @param session the session
     * @param username the username
     * @param password the password
     * @return the boolean
     */
    Boolean userValidate(HttpSession session, String username, String password);
    
    /**
     * Find by user name.
     *
     * @param username the username
     * @return the user
     */
    User findByUserName(String username);
    
    /**
     * Find users.
     *
     * @return the list
     */
    List<User> findUsers();
    
    /**
     * Find users by username.
     *
     * @param username the username
     * @return the list
     */
    List<User> findUsersByUsername(String username);
    
    /**
     * Find by user id.
     *
     * @param userId the user id
     * @return the user
     */
    User findByUserId(Integer userId);
    
    /**
     * Delete user.
     *
     * @param user the user
     */
    void deleteUser(User user);
    
    /**
     * Save user.
     *
     * @param user the user
     * @return the user
     */
    User saveUser(User user);

}
