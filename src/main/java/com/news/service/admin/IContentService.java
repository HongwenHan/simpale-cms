/*
 * @(#)IContentService.java 2018年6月16日下午12:53:47
 * news
 * Copyright 2018 Thuisoft, Inc. All rights reserved.
 * THUNISOFT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.news.service.admin;

import java.util.List;

import org.springframework.data.domain.Page;

import com.news.entity.Channel;
import com.news.entity.Content;

// TODO: Auto-generated Javadoc
/**
 * IContentService.
 *
 * @author hhong
 * @version 1.0
 */
public interface IContentService {

    /**
     * Find contents by title.
     *
     * @param title the title
     * @return the list
     */
    List<Content> findContentsByTitle(String title);

    /**
     * Find by content id.
     *
     * @param contentId the content id
     * @return the content
     */
    Content findByContentId(Integer contentId);

    /**
     * Delete user.
     *
     * @param content the content
     */
    void deleteUser(Content content);

    /**
     * Find contents.
     *
     * @return the list
     */
    List<Content> findContents();

    /**
     * Save content.
     *
     * @param content the content
     * @return the content
     */
    Content saveContent(Content content);

    /**
     * Find contents by channel id.
     *
     * @param channelId the channel id
     * @return the list
     */
    List<Content> findContentsByChannel(Channel channel);
    
    /**
     * Find page contents by channel.
     *
     * @param channel the channel
     * @param page the page
     * @return the list
     */
    Page<Content> findPageContentsByChannel(Channel channel, Integer page);
    
}
