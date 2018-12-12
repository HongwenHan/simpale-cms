/*
 * @(#)IContentRepositiry.java 2018年6月16日下午1:37:55
 * news
 * Copyright 2018 Thuisoft, Inc. All rights reserved.
 * THUNISOFT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.news.dao.admin;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.news.entity.Channel;
import com.news.entity.Content;

// TODO: Auto-generated Javadoc
/**
 * IContentRepositiry.
 *
 * @author hhong
 * @version 1.0
 */
public interface IContentRepositiry  extends JpaRepository<Content, Long> {
    
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
     * Find contents by channel.
     *
     * @param channel the channel
     * @return the list
     */
    List<Content> findContentsByChannel(Channel channel);
    
    Page<Content> findContentsByChannel(Channel channel, Pageable pageable); 
}
