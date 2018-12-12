/*
 * @(#)IChannelService.java 2018年5月28日下午1:16:36
 * news
 * Copyright 2018 Thuisoft, Inc. All rights reserved.
 * THUNISOFT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.news.service.admin;

import java.util.List;
import java.util.Map;

import com.news.entity.Channel;

// TODO: Auto-generated Javadoc
/**
 * IChannelService.
 *
 * @author hanhw
 * @version 1.0
 */
public interface IChannelService {

    /**
     * Find channel by channel id.
     *
     * @param channelId the channel id
     * @return the channel
     */
    Channel findChannelByChannelId(Integer channelId);

    /**
     * Save channel.
     *
     * @param channel the channel
     * @return the channel
     */
    Channel saveChannel(Channel channel);

    /**
     * Gets the channel list by P id.
     *
     * @param pId the id
     * @return the channel list by P id
     */
    List<Channel> getChannelListByPId(Integer pId);

    /**
     * Delete by channel id.
     *
     * @param channel the channel
     */
    void deleteByChannelId(Channel channel);

    /**
     * Gets the channel ztree.
     *
     * @param channels the channels
     * @return the channel ztree
     */
    List<Map<String, Object>> getChannelZtree(List<Channel> channels, Integer type);

    /**
     * Find channel by channel path.
     *
     * @param channelPath the channel path
     * @return the channel
     */
    Channel findChannelByChannelPath(String channelPath);
}
