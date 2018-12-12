/*
 * @(#)Content.java 2018年5月22日下午2:33:18
 * news
 * Copyright 2018 Thuisoft, Inc. All rights reserved.
 * THUNISOFT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.news.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

// TODO: Auto-generated Javadoc
/**
 * Content.
 *
 * @author hanhw
 * @version 1.0
 */
@Entity
public class Content {

    /** The content id. */
    @Id
    @GeneratedValue
    private Integer contentId;

    /** The title. */
    @Column(nullable = false, unique = false)
    private String title;

    /** The author. */
    @Column(nullable = true, unique = false)
    private String author;

    /** The organ. */
    @Column(nullable = true, unique = false)
    private String organ;

    /** The link. */
    @Column(nullable = true, unique = false)
    private String link;

    /** The txt. */
    @Column(nullable = true, unique = false)
    private String txt;

    /** The create time. */
    @Column(nullable = false, unique = false)
    private String createTime;

    /** The title pic. */
    @Column(nullable = true, unique = false)
    private String titlePic;

    /** The pictures. */
    @Transient
    private List<Picture> pictures;

    /** The attachements. */
    @Transient
    private List<Attachment> attachements;
    
    /** The roles. */
    @ManyToOne(fetch = FetchType.EAGER) // 立即从数据库中进行加载数据;
    @JoinTable(name = "ContentChannel", joinColumns = {
            @JoinColumn(name = "contentId") }, inverseJoinColumns = {
                    @JoinColumn(name = "channeld") })
    private Channel channel;

    /**
     * Gets the channel.
     *
     * @return the channel
     */
    public Channel getChannel() {
        return channel;
    }

    /**
     * Sets the channel.
     *
     * @param channel the channel to set
     */
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    /**
     * Gets the content id.
     *
     * @return the contentId
     */
    public Integer getContentId() {
        return contentId;
    }

    /**
     * Sets the content id.
     *
     * @param contentId            the contentId to set
     */
    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    /**
     * Gets the title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title.
     *
     * @param title            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the author.
     *
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author.
     *
     * @param author            the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the organ.
     *
     * @return the organ
     */
    public String getOrgan() {
        return organ;
    }

    /**
     * Sets the organ.
     *
     * @param organ            the organ to set
     */
    public void setOrgan(String organ) {
        this.organ = organ;
    }

    /**
     * Gets the link.
     *
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * Sets the link.
     *
     * @param link            the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * Gets the txt.
     *
     * @return the txt
     */
    public String getTxt() {
        return txt;
    }

    /**
     * Sets the txt.
     *
     * @param txt            the txt to set
     */
    public void setTxt(String txt) {
        this.txt = txt;
    }

    /**
     * Gets the creates the time.
     *
     * @return the createTime
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * Sets the creates the time.
     *
     * @param createTime            the createTime to set
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * Gets the title pic.
     *
     * @return the titlePic
     */
    public String getTitlePic() {
        return titlePic;
    }

    /**
     * Sets the title pic.
     *
     * @param titlePic            the titlePic to set
     */
    public void setTitlePic(String titlePic) {
        this.titlePic = titlePic;
    }

    /**
     * Gets the pictures.
     *
     * @return the pictures
     */
    public List<Picture> getPictures() {
        return pictures;
    }

    /**
     * Sets the pictures.
     *
     * @param pictures            the pictures to set
     */
    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    /**
     * Gets the attachements.
     *
     * @return the attachements
     */
    public List<Attachment> getAttachements() {
        return attachements;
    }

    /**
     * Sets the attachements.
     *
     * @param attachements            the attachements to set
     */
    public void setAttachements(List<Attachment> attachements) {
        this.attachements = attachements;
    }

}
