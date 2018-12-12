/*
 * @(#)IUserMapper.java 2018年4月22日下午10:26:06
 * news
 * Copyright 2018 Thuisoft, Inc. All rights reserved.
 * THUNISOFT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.news.dao.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.news.entity.User;

// TODO: Auto-generated Javadoc
/**
 * IUserMapper.
 *
 * @author hhong
 * @version 1.0
 */
public interface IUserRepositiry extends JpaRepository<User, Long> {

    /**
     * Find by username.
     *
     * @param username the username
     * @return the user
     */
    User findByUsername(String username);

    /** (non-Javadoc)
     * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
     */
    List<User> findAll();

    /**
     * Find users by username.
     *
     * @param username the username
     * @return the list
     */
    List<User> findUsersByUsername(String username);

    /**
     * Find by id.
     *
     * @param id the id
     * @return the user
     */
    User findById(Integer id);

}
