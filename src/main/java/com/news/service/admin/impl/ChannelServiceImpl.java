/*
 * @(#)ChannelServiceImpl.java 2018年5月28日下午1:16:54
 * news
 * Copyright 2018 Thuisoft, Inc. All rights reserved.
 * THUNISOFT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.news.service.admin.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.dao.admin.IChannelRepositiry;
import com.news.entity.Channel;
import com.news.service.admin.IChannelService;

// TODO: Auto-generated Javadoc
/**
 * ChannelServiceImpl.
 *
 * @author hanhw
 * @version 1.0
 */
@Service("channelService")
public class ChannelServiceImpl implements IChannelService {

    /** The channel dao. */
    @Autowired
    private IChannelRepositiry channelDao;

    /**
     * (non-Javadoc).
     *
     * @param channelId the channel id
     * @return the channel
     * @see com.news.service.admin.IChannelService#findChannelByChannelId(java.lang.Integer)
     */
    @Override
    public Channel findChannelByChannelId(Integer channelId) {
        return channelDao.findChannelByChannelId(channelId);
    }

    /**
     * (non-Javadoc).
     *
     * @param channel the channel
     * @return the channel
     * @see com.news.service.admin.IChannelService#saveChannel(com.news.entity.Channel)
     */
    @Override
    public Channel saveChannel(Channel channel) {
        return channelDao.save(channel);
    }

    /**
     * (non-Javadoc).
     *
     * @param pId the id
     * @return the channel list by P id
     * @see com.news.service.admin.IChannelService#getChannelListByPId(java.lang.Object)
     */
    @Override
    public List<Channel> getChannelListByPId(Integer pId) {
        return channelDao.findChannelsByParentId(pId);
    }

    /**
     * (non-Javadoc).
     *
     * @param channel the channel
     * @see com.news.service.admin.IChannelService#deleteByChannelId(java.lang.Integer)
     */
    @Override
    public void deleteByChannelId(Channel channel) {
        channelDao.delete(channel);
    }

    /**
     * (non-Javadoc).
     *
     * @param channels the channels
     * @return the channel ztree
     * @see com.news.service.admin.IChannelService#getChannelZtree()
     */
    @Override
    public List<Map<String, Object>> getChannelZtree(List<Channel> channels, Integer type) {
        List<Map<String, Object>> ztree = new ArrayList<Map<String, Object>>();
        for (Channel channel : channels) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", channel.getChannelName());
            map.put("id", channel.getChannelId());
            List<Channel> childs = channelDao
                    .findChannelsByParentId(channel.getChannelId());
            if (CollectionUtils.isEmpty(childs)) {
                map.put("open", false);
                map.put("isParent", false);
                if(type == 2){
                    map.put("url",
                        "/cms/editChannel?channelId=" + channel.getChannelId());
                } else{
                    map.put("url",
                        "/cms/content?channelId=" + channel.getChannelId());
                }
            } else {
                map.put("open", true);
                map.put("isParent", true);
                List<Map<String, Object>> childTree = getChannelZtree(childs, type);
                map.put("children", childTree);
            }
            ztree.add(map);
        }
        return ztree;
    }

    /**
     *  (non-Javadoc).
     *
     * @param channelPath the channel path
     * @return the channel
     * @see com.news.service.admin.IChannelService#findChannelByChannelPath(java.lang.String)
     */
    @Override
    public Channel findChannelByChannelPath(String channelPath) {
        return channelDao.findChannelByChannelPath(channelPath);
    }
}
