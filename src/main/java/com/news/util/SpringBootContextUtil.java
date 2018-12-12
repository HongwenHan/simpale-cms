/*
 * @(#)SpringBootContextUtil.java 2018年6月17日下午7:12:53
 * news
 * Copyright 2018 Thuisoft, Inc. All rights reserved.
 * THUNISOFT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.news.util;

import org.springframework.context.ApplicationContext;

/**
 * SpringBootContextUtil
 * @author hhong
 * @version 1.0
 *
 */
public class SpringBootContextUtil {
    private static ApplicationContext applicationContext;  
    
    //获取上下文  
    public static ApplicationContext getApplicationContext() {  
        return applicationContext;  
    }  
  
    //设置上下文  
    public static void setApplicationContext(ApplicationContext applicationContext) {  
        SpringBootContextUtil.applicationContext = applicationContext;  
    }  
  
    //通过名字获取上下文中的bean  
    public static Object getBean(String name){  
        return applicationContext.getBean(name);  
    }  
      
    //通过类型获取上下文中的bean  
    public static Object getBean(Class<?> requiredType){  
        return applicationContext.getBean(requiredType);  
    }  
}
