/*
 * @(#)DymanicAct.java 2018年6月17日下午3:14:16
 * news
 * Copyright 2018 Thuisoft, Inc. All rights reserved.
 * THUNISOFT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.news.controller.front;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.news.entity.Channel;
import com.news.entity.Content;
import com.news.service.admin.IChannelService;
import com.news.service.admin.IContentService;
import com.news.util.RequestUtils;
import com.news.util.StrUtils;

/**
 * DymanicAct
 * @author hhong
 * @version 1.0
 *
 */
@Controller
public class DymanicAct {

    @Autowired
    private IChannelService channelService;
    
    @Autowired
    private IContentService contentService;
    
    @RequestMapping(value="front/index")
    public String index(HttpServletRequest request,
            Map<String, Object> map){
        Channel channel = channelService.findChannelByChannelPath("ttxw");
        List<Content> contents = contentService.findContentsByChannel(channel);
        Page<Content> zxzxs = contentService.findPageContentsByChannel(null, 1);
        for(Content c : zxzxs){
            c.setTitle(StrUtils.textCut(c.getTitle(), 17, "..."));
        }
        map.put("zxzxs", zxzxs);
        if(CollectionUtils.isNotEmpty(contents)){
            Content content = contents.get(0);
            String txt = StrUtils.htmlCut(content.getTxt(), 300, "...");
            String title = StrUtils.htmlCut(content.getTitle(), 26, "...");
            content.setTxt(txt);
            content.setTitle(title);
            map.put("syContent", content);
            String base = request.getContextPath();
            map.put("base", base);
        }
        return "front/index";
    }
    
    @RequestMapping(value="front/info")
    public String info(HttpServletRequest request,
            Map<String, Object> map){
        String contentId = RequestUtils.getQueryParam(request, "contentId");
        Content content = contentService.findByContentId(Integer.valueOf(contentId));
        String base = request.getContextPath();
        map.put("base", base);
        map.put("content", content);
        return "front/info";
    }
    
    @RequestMapping(value="front/list/{channelPath}/{page}")
    public String listPage(HttpServletRequest request, 
            @PathVariable String channelPath, @PathVariable Integer page, 
            Map<String, Object> map){
        String base = request.getContextPath();
        map.put("base", base);
        if(channelPath.equals("gd")){
            return "front/setting";
        }
        Channel channel = channelService.findChannelByChannelPath(channelPath);
        Page<Content> contents = contentService.findPageContentsByChannel(channel, page);
        for(Content c : contents){
            c.setTitle(StrUtils.textCut(c.getTitle(), 50, "..."));
        }
        map.put("contents", contents);
        map.put("page", page);
        map.put("total", contents.getTotalPages());
        map.put("path",base+"/front/list/"+channelPath+"/");
        if(channelPath.equals("tpkd")){
            return "front/list2";
        } else if(channelPath.equals("yszx") || channelPath.equals("kjzx") || channelPath.equals("yxzx")){
            return "front/list3";
        }else if(channelPath.equals("gyyh")){
            return "front/info2";
        }else if(channelPath.equals("gd")){
            return "front/setting";
        }else{
            return "front/list";
        }
    }
}
