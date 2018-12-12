/*
 * @(#)ChannelAct.java 2018年5月24日下午1:15:27
 * news
 * Copyright 2018 Thuisoft, Inc. All rights reserved.
 * THUNISOFT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.news.controller.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.news.entity.Channel;
import com.news.entity.User;
import com.news.service.admin.IChannelService;
import com.news.util.RequestUtils;

/**
 * ChannelAct
 * 
 * @author hanhw
 * @version 1.0
 *
 */
@Controller
public class ChannelAct {

    @Autowired
    private IChannelService channelService;

    @RequestMapping(value = "/channel")
    public String channelList(Map<String, Object> map) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        map.put("user", user);
        return "admin/channel";
    }

    @RequestMapping(value = "/addChannel")
    public String channelAdd(HttpServletRequest request,
            Map<String, Object> map) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Integer pId = getParentId(request);
        Channel channel = channelService.findChannelByChannelId(pId);
        List<Channel> channels = channelService.getChannelListByPId(null);
        List<Map<String, Object>> ztree = channelService.getChannelZtree(channels, 2);
        map.put("ztree", ztree);
        map.put("channel", channel);
        map.put("pId", pId);
        map.put("user", user);
        return "admin/addChannel";
    }

    @RequestMapping(value = "/saveChannel")
    public String channelSave(HttpServletRequest request,
            Map<String, Object> map) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Channel channel = getChannel(request);
        channel = channelService.saveChannel(channel);
        Integer pId = getParentId(request);
        List<Channel> channels = channelService.getChannelListByPId(pId);
        map.put("channels", channels);
        map.put("user", user);
        return "redirect:";
    }

    @RequestMapping(value = "/deleteChannel")
    public String deleteChannel(HttpServletRequest request,
            Map<String, Object> map) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String channelId = RequestUtils.getQueryParam(request, "channelId");
        channelService.deleteByChannelId(channelService
                .findChannelByChannelId(Integer.valueOf(channelId)));
        Integer pId = getParentId(request);
        List<Channel> channels = channelService.getChannelListByPId(pId);
        map.put("channels", channels);
        map.put("user", user);
        return "redirect:";
    }

    @RequestMapping(value = "/editChannel")
    public String editChannel(HttpServletRequest request,
            Map<String, Object> map) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String channelId = RequestUtils.getQueryParam(request, "channelId");
        Channel channel = channelService
                .findChannelByChannelId(Integer.valueOf(channelId));
        List<Channel> channels = channelService.getChannelListByPId(null);
        List<Map<String, Object>> ztree = channelService.getChannelZtree(channels, 2);
        map.put("ztree", ztree);
        map.put("channel", channel);
        map.put("user", user);
        return "admin/editChannel";
    }

    /**
     * @param request
     * @return
     */
    private Integer getParentId(HttpServletRequest request) {
        String pId = RequestUtils.getQueryParam(request, "pId");
        if (StringUtils.isNotBlank(pId)) {
            return Integer.parseInt(pId);
        } else {
            return null;
        }
    }

    /**
     * @param request
     * @return
     */
    private Channel getChannel(HttpServletRequest request) {
        Channel channel = new Channel();
        String channelId = RequestUtils.getQueryParam(request, "channelId");
        if (StringUtils.isNotBlank(channelId)) {
            channel.setChannelId(Integer.valueOf(channelId));
        }
        String pId = RequestUtils.getQueryParam(request, "pId");
        if (StringUtils.isNotBlank(pId)) {
            channel.setParentId(Integer.valueOf(pId));
        }
        String channelName = RequestUtils.getQueryParam(request, "channelName");
        if (StringUtils.isNotBlank(channelName)) {
            channel.setChannelName(channelName);
        }
        String channelPath = RequestUtils.getQueryParam(request, "channelPath");
        if (StringUtils.isNotBlank(channelPath)) {
            channel.setChannelPath(channelPath);
        }
        String channelModel = RequestUtils.getQueryParam(request,
                "channelModel");
        if (StringUtils.isNotBlank(channelModel)) {
            channel.setModelPath(channelModel);
        }
        String channelShow = RequestUtils.getQueryParam(request, "available");
        if (StringUtils.isNotBlank(channelShow)) {
            channel.setAvailable(Boolean.valueOf(channelShow));
        }
        String channelDescript = RequestUtils.getQueryParam(request,
                "channelDescript");
        if (StringUtils.isNotBlank(channelDescript)) {
            channel.setDescript(channelDescript);
        }
        return channel;
    }
}
