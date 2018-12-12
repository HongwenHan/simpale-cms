/*
 * @(#)Permission.java 2018年5月21日下午2:28:32
 * news
 * Copyright 2018 Thuisoft, Inc. All rights reserved.
 * THUNISOFT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.news.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

// TODO: Auto-generated Javadoc
/**
 * Permission.
 *
 * @author hanhw
 * @version 1.0
 */
@Entity
public class Permission implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2L;

    /** The id. */
    @Id
    @GeneratedValue
    @Column(nullable=false, unique=true)
    private Integer id;// 主键.

    /** The name. */
    @Column(nullable=false, unique=false)
    private String name;// 名称.

    /** The resource type. */
    @Column(columnDefinition = "enum('menu','button')")
    private String resourceType;// 资源类型，[menu|button]

    /** The url. */
    @Column(nullable=false, unique=false)
    private String url;// 资源路径.

    /** The permission. */
    @Column(nullable=false, unique=false)
    private String permission; // 权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view

    /** The parent id. */
    @Column(nullable=false, unique=false)
    private Long parentId; // 父编号

    /** The parent ids. */
    @Column(nullable=false, unique=false)
    private String parentIds; // 父编号列表

    /** The available. */
    @Column(nullable=false, unique=false)
    private Boolean available = Boolean.FALSE;

    /** The roles. */
    @ManyToMany
    @JoinTable(name = "RolePermission", joinColumns = {
            @JoinColumn(name = "permissionId") }, inverseJoinColumns = {
                    @JoinColumn(name = "roleId") })
    private List<Role> roles;

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
     * @param id            the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the resource type.
     *
     * @return the resourceType
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * Sets the resource type.
     *
     * @param resourceType            the resourceType to set
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * Gets the url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the url.
     *
     * @param url            the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets the permission.
     *
     * @return the permission
     */
    public String getPermission() {
        return permission;
    }

    /**
     * Sets the permission.
     *
     * @param permission            the permission to set
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**
     * Gets the parent id.
     *
     * @return the parentId
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * Sets the parent id.
     *
     * @param parentId            the parentId to set
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * Gets the parent ids.
     *
     * @return the parentIds
     */
    public String getParentIds() {
        return parentIds;
    }

    /**
     * Sets the parent ids.
     *
     * @param parentIds            the parentIds to set
     */
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    /**
     * Gets the available.
     *
     * @return the available
     */
    public Boolean getAvailable() {
        return available;
    }

    /**
     * Sets the available.
     *
     * @param available            the available to set
     */
    public void setAvailable(Boolean available) {
        this.available = available;
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
     * @param roles            the roles to set
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}
