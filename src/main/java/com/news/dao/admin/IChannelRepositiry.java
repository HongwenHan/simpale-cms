/*
 * @(#)IChannelRepositiry.java 2018年5月28日下午1:19:04
 * news
 * Copyright 2018 Thuisoft, Inc. All rights reserved.
 * THUNISOFT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.news.dao.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.news.entity.Channel;

// TODO: Auto-generated Javadoc
/**
 * IChannelRepositiry.
 *
 * @author hanhw
 * @version 1.0
 */
public interface IChannelRepositiry extends JpaRepository<Channel, Long> {

    /**
     * Find channel by channel id.
     *
     * @param channelId the channel id
     * @return the channel
     */
    Channel findChannelByChannelId(Integer channelId);

    /**
     * Find channels by parent id.
     *
     * @param parentId the parent id
     * @return the list
     */
    List<Channel> findChannelsByParentId(Integer parentId);
    
    /**
     * Find channel by channel path.
     *
     * @param channelPath the channel path
     * @return the channel
     */
    Channel findChannelByChannelPath(String channelPath);

}
