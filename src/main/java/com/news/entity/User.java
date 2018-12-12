/*
 * @(#)User.java 2018年3月14日下午4:49:21
 * newsofcms Maven Webapp
 * Copyright 2018 Thuisoft, Inc. All rights reserved.
 * THUNISOFT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.news.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

// TODO: Auto-generated Javadoc
/**
 * User.
 *
 * @author hanhw
 * @version 1.0
 */
@Entity
public class User implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    @Id
    @GeneratedValue
    private Integer id;

    /** The username. */
    @Column(nullable = false, unique = true)
    private String username;

    /** The password. */
    @Column(nullable = false, unique = false)
    private String password;

    /** The realname. */
    @Column(nullable = false, unique = false)
    private String realname;

    /** The salt. */
    @Column(nullable = true, unique = false)
    private String salt;// 加密密码的盐

    /** The state. */
    @Column(nullable = false, unique = false)
    private Boolean state;// 用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 ,
                       // 1:正常状态,2：用户被锁定.
    /** The is admin. */
    @Column(nullable = false, unique = false)
    private Boolean isAdmin;

    /** The roles. */
    @ManyToMany(fetch = FetchType.EAGER) // 立即从数据库中进行加载数据;
    @JoinTable(name = "UserRole", joinColumns = {
            @JoinColumn(name = "uid") }, inverseJoinColumns = {
                    @JoinColumn(name = "roleId") })
    private List<Role> roles;

    /**
     * Gets the salt.
     *
     * @return the salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Sets the salt.
     *
     * @param salt            the salt to set
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * Gets the state.
     *
     * @return the state
     */
    public Boolean getState() {
        return state;
    }

    /**
     * Sets the state.
     *
     * @param state            the state to set
     */
    public void setState(Boolean state) {
        this.state = state;
    }

    /**
     * Gets the roles.
     *
     * @return the roles
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Sets the roles.
     *
     * @param roles
     *            the roles to set
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username
     *            the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the realname.
     *
     * @return the realname
     */
    public String getRealname() {
        return realname;
    }

    /**
     * Sets the realname.
     *
     * @param realname
     *            the realname to set
     */
    public void setRealname(String realname) {
        this.realname = realname;
    }

    /**
     * Gets the checks if is admin.
     *
     * @return the isAdmin
     */
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     * Sets the checks if is admin.
     *
     * @param isAdmin
     *            the isAdmin to set
     */
    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * Gets the credentials salt.
     *
     * @return the credentials salt
     */
    public String getCredentialsSalt(){
        return this.username+this.salt;
    }
}
