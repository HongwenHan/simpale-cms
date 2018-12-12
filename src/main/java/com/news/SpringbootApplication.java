/*
 * @(#)SpringbootApplication.java 2018年3月14日下午3:22:41
 * newsofcms Maven Webapp
 * Copyright 2018 Thuisoft, Inc. All rights reserved.
 * THUNISOFT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.news.util.SpringBootContextUtil;

/**
 * SpringbootApplication
 * @author hanhw
 * @version 1.0
 *
 */
@SpringBootApplication
public class SpringbootApplication {

    public static void main(String[] args) throws Exception {
        ApplicationContext app = SpringApplication.run(SpringbootApplication.class, args);
        SpringBootContextUtil.setApplicationContext(app); 
    }

}
