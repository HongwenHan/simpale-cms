/*
 * @(#)Welcome.java 2018年3月8日下午3:52:14
 * newsofcms Maven Webapp
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
 * Welcome
 * 
 * @author hanhw
 * @version 1.0
 *
 */
@Controller
public class Welcome {

    @Autowired
    private IChannelService channelService;

    @RequestMapping("/")
    public String welcome(HttpServletRequest request, Map<String, Object> map) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Integer parentId = getParentId(request);
        List<Channel> channels = channelService.getChannelListByPId(parentId);
        List<Map<String, Object>> ztree = channelService.getChannelZtree(channels, 2);
        map.put("ztree", ztree);
        map.put("pId", parentId);
        map.put("channels", channels);
        map.put("user", user);
        map.put("isHome", true);
        return "admin/home";
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
    
    @RequestMapping(value="setting")
    public String setting(HttpServletRequest request, Map<String, Object> map){
        return "admin/setting";
    }
}
