/*
 * @(#)Channel.java 2018年5月22日下午2:34:04
 * news
 * Copyright 2018 Thuisoft, Inc. All rights reserved.
 * THUNISOFT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
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
import javax.persistence.OneToMany;

// TODO: Auto-generated Javadoc
/**
 * Channel.
 *
 * @author hanhw
 * @version 1.0
 */
@Entity
public class Channel {

    /** The channel id. */
    @Id
    @GeneratedValue
    private Integer channelId;

    /** The channel name. */
    @Column(nullable = false, unique = false)
    private String channelName;

    /** The channel path. */
    @Column(nullable = false, unique = true)
    private String channelPath;

    /** The parent id. */
    @Column(nullable = true, unique = false)
    private Integer parentId;

    /** The available. */
    @Column(nullable = true, unique = false)
    private Boolean available;

    /** The model path. */
    @Column(nullable = true, unique = false)
    private String modelPath;

    /** The descript. */
    @Column(nullable = true, unique = false)
    private String descript;

    /**
     * Gets the model path.
     *
     * @return the modelPath
     */
    public String getModelPath() {
        return modelPath;
    }

    /**
     * Sets the model path.
     *
     * @param modelPath            the modelPath to set
     */
    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }

    /**
     * Gets the descript.
     *
     * @return the descript
     */
    public String getDescript() {
        return descript;
    }

    /**
     * Sets the descript.
     *
     * @param descript            the descript to set
     */
    public void setDescript(String descript) {
        this.descript = descript;
    }

    /**
     * Gets the channel id.
     *
     * @return the channelId
     */
    public Integer getChannelId() {
        return channelId;
    }

    /**
     * Sets the channel id.
     *
     * @param channelId            the channelId to set
     */
    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    /**
     * Gets the channel name.
     *
     * @return the channelName
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * Sets the channel name.
     *
     * @param channelName            the channelName to set
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * Gets the channel path.
     *
     * @return the channelPath
     */
    public String getChannelPath() {
        return channelPath;
    }

    /**
     * Sets the channel path.
     *
     * @param channelPath            the channelPath to set
     */
    public void setChannelPath(String channelPath) {
        this.channelPath = channelPath;
    }

    /**
     * Gets the parent id.
     *
     * @return the parentId
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * Sets the parent id.
     *
     * @param parentId            the parentId to set
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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

}
