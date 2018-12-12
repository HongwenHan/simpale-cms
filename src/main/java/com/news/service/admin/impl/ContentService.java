/*
 * @(#)ContentService.java 2018年6月16日下午12:54:25
 * news
 * Copyright 2018 Thuisoft, Inc. All rights reserved.
 * THUNISOFT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.news.service.admin.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.news.dao.admin.IChannelRepositiry;
import com.news.dao.admin.IContentRepositiry;
import com.news.entity.Channel;
import com.news.entity.Content;
import com.news.service.admin.IContentService;

// TODO: Auto-generated Javadoc
/**
 * ContentService.
 *
 * @author hhong
 * @version 1.0
 */
@Service("contentService")
public class ContentService implements IContentService{
    
    /** The content dao. */
    @Autowired
    private IContentRepositiry contentDao;
    
    /** The channel dao. */
    @Autowired
    private IChannelRepositiry channelDao;

    /**
     *  (non-Javadoc).
     *
     * @param title the title
     * @return the list
     * @see com.news.service.admin.IContentService#findContentsByTitle(java.lang.String)
     */
    @Override
    public List<Content> findContentsByTitle(String title) {
        List<Content> contents = new ArrayList<Content>();
        if(StringUtils.isNotBlank(title)){
            contents = contentDao.findContentsByTitle(title);
        }else{
            contents = contentDao.findAll();
        }
        return contents;
    }

    /**
     *  (non-Javadoc).
     *
     * @param contentId the content id
     * @return the content
     * @see com.news.service.admin.IContentService#findByContentId(java.lang.Integer)
     */
    @Override
    public Content findByContentId(Integer contentId) {
        return contentDao.findByContentId(contentId);
    }

    /**
     *  (non-Javadoc).
     *
     * @param content the content
     * @see com.news.service.admin.IContentService#deleteUser(com.news.entity.Content)
     */
    @Override
    public void deleteUser(Content content) {
        contentDao.delete(content);
    }

    /**
     *  (non-Javadoc).
     *
     * @return the list
     * @see com.news.service.admin.IContentService#findContents()
     */
    @Override
    public List<Content> findContents() {
        return contentDao.findAll();
    }

    /**
     *  (non-Javadoc).
     *
     * @param content the content
     * @return the content
     * @see com.news.service.admin.IContentService#saveContent(com.news.entity.Content)
     */
    @Override
    public Content saveContent(Content content) {
        return contentDao.save(content);
    }

    /**
     *  (non-Javadoc).
     *
     * @param channelId the channel id
     * @return the list
     * @see com.news.service.admin.IContentService#findContentsByChannelId(java.lang.Integer)
     */
    @Override
    public List<Content> findContentsByChannel(Channel channel) {
        List<Content> contents = contentDao.findContentsByChannel(channel);
        return contents;
    }

    /**
     *  (non-Javadoc).
     *
     * @param channel the channel
     * @param page the page
     * @return the list
     * @see com.news.service.admin.IContentService#findPageContentsByChannel(com.news.entity.Channel, java.lang.Integer)
     */
    @Override
    public Page<Content> findPageContentsByChannel(Channel channel, Integer page) {
        Pageable pageable = new PageRequest(page - 1, 10, Sort.Direction.DESC, "createTime"); 
        if(channel != null){
            Page<Content> contents = contentDao.findContentsByChannel(channel, pageable);
            return contents;
        }else{
            Page<Content> contents = contentDao.findAll(pageable);
            return contents;
        }
    }

}
