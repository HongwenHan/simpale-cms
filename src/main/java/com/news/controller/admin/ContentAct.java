/*
 * @(#)ContentAct.java 2018年6月16日下午1:31:05
 * news
 * Copyright 2018 Thuisoft, Inc. All rights reserved.
 * THUNISOFT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.news.controller.admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.news.entity.Channel;
import com.news.entity.Content;
import com.news.entity.User;
import com.news.service.admin.IChannelService;
import com.news.service.admin.IContentService;
import com.news.util.RequestUtils;

/**
 * ContentAct
 * @author hhong
 * @version 1.0
 *
 */
@Controller
public class ContentAct {

    @Autowired
    private IContentService contentService;
    
    @Autowired
    private IChannelService channelService;
    
    @RequestMapping(value="content")
    public String ContentList(HttpServletRequest request,
            Map<String, Object> map){
        String channelId = RequestUtils.getQueryParam(request, "channelId");
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String title = RequestUtils.getQueryParam(request, "searchTitle");
        List<Content> contents = new ArrayList<Content>();
        if(StringUtils.isNotBlank(channelId)){
            Channel channel = channelService.findChannelByChannelId(Integer.valueOf(channelId));
            contents = contentService.findContentsByChannel(channel);
        }else{
            contents = contentService.findContentsByTitle(title);
        }
        List<Channel> channels = channelService.getChannelListByPId(null);
        List<Map<String, Object>> ztree = channelService.getChannelZtree(channels, 1);
        map.put("ztree", ztree);
        map.put("contents", contents);
        map.put("user", user);
        map.put("isContent", true);
        return "admin/content";
    }
    
    @RequestMapping(value="addContent")
    public String addContent(HttpServletRequest request,
            Map<String, Object> map){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        List<Channel> channels = channelService.getChannelListByPId(null);
        List<Map<String, Object>> ztree = channelService.getChannelZtree(channels, 1);
        map.put("ztree", ztree);
        map.put("channels", channels);
        map.put("user", user);
        return "admin/addContent";
    }
    
    @RequestMapping(value="saveContent")
    public String saveContent(HttpServletRequest request,
            Map<String, Object> map){
        Content content = getContent(request);
        content = contentService.saveContent(content);
        return "redirect:content";
    }
    
    /**
     * @param request
     * @return
     */
    private Content getContent(HttpServletRequest request) {
        Content content = new Content();
        String createDate = getNow();
        content.setCreateTime(createDate);
        String contentId = RequestUtils.getQueryParam(request, "contentId");
        if(StringUtils.isNotBlank(contentId)){
            content.setContentId(Integer.valueOf(contentId));
        }
        String channelId = RequestUtils.getQueryParam(request, "channelId");
        if(StringUtils.isNotBlank(channelId)){
            Channel channel = channelService.findChannelByChannelId(Integer.valueOf(channelId));
            content.setChannel(channel);
        }
        String title = RequestUtils.getQueryParam(request, "title");
        if(StringUtils.isNotBlank(title)){
            content.setTitle(title);
        }
        String author = RequestUtils.getQueryParam(request, "author");
        if(StringUtils.isNotBlank(author)){
            content.setAuthor(author);
        }
        String organ = RequestUtils.getQueryParam(request, "organ");
        if(StringUtils.isNotBlank(organ)){
            content.setOrgan(organ);
        }
        String link = RequestUtils.getQueryParam(request, "link");
        if(StringUtils.isNotBlank(link)){
            content.setLink(link);
        }
        String titlePic = RequestUtils.getQueryParam(request, "titlePic");
        if(StringUtils.isNotBlank(titlePic)){
            content.setTitlePic(titlePic);
        }
        String contentTxt = RequestUtils.getQueryParam(request, "contentTxt");
        if(StringUtils.isNotBlank(contentTxt)){
            content.setTxt(contentTxt);
        }
        return content;
    }

    /**
     * @return
     */
    private String getNow() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    @RequestMapping(value="editContent")
    public String editContent(HttpServletRequest request,
            Map<String, Object> map){
        User syuser = (User) SecurityUtils.getSubject().getPrincipal();
        String contentId = RequestUtils.getQueryParam(request, "contentId");
        Content content = contentService.findByContentId(Integer.valueOf(contentId));
        List<Channel> channels = channelService.getChannelListByPId(null);
        List<Map<String, Object>> ztree = channelService.getChannelZtree(channels, 1);
        map.put("ztree", ztree);
        map.put("channels", channels);
        map.put("content", content);
        map.put("user", syuser);
        return "admin/editContent";
    }
    
    @RequestMapping(value="deleteContent")
    public String deleteContent(HttpServletRequest request,
            Map<String, Object> map){
        User syuser = (User) SecurityUtils.getSubject().getPrincipal();
        String contentId = RequestUtils.getQueryParam(request, "contentId");
        Content content = contentService.findByContentId(Integer.valueOf(contentId));
        contentService.deleteUser(content);
        map.put("user", syuser);
        return "redirect:content";
    }
    
}
