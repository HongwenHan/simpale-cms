/*
 * @(#)Role.java 2018年5月3日下午6:31:29 news Copyright 2018 Thuisoft, Inc. All
 * rights reserved. THUNISOFT PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package com.news.entity;

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
 * Role.
 *
 * @author hhong
 * @version 1.0
 */
@Entity
public class Role {

    /** The role id. */
    @Id
    @GeneratedValue
    private Integer roleId;

    /** The role name. */
    @Column(nullable = false, unique = false)
    private String roleName;

    /** The available. */
    @Column(nullable = false, unique = false)
    private Boolean available;

    /** The permissions. */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "RolePermission", joinColumns = {
            @JoinColumn(name = "roleId") }, inverseJoinColumns = {
                    @JoinColumn(name = "permissionId") })
    private List<Permission> permissions;

    /**
     * Gets the permissions.
     *
     * @return the permissions
     */
    public List<Permission> getPermissions() {
        return permissions;
    }

    /**
     * Sets the permissions.
     *
     * @param permissions            the permissions to set
     */
    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    /**
     * Gets the role id.
     *
     * @return the roleId
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * Sets the role id.
     *
     * @param roleId
     *            the roleId to set
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * Gets the role name.
     *
     * @return the roleName
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets the role name.
     *
     * @param roleName
     *            the roleName to set
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
     * @param available
     *            the available to set
     */
    public void setAvailable(Boolean available) {
        this.available = available;
    }

}
