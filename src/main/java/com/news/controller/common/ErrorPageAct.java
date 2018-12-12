/*
 * @(#)ErrorPageAct.java 2018年5月23日上午10:58:22
 * news
 * Copyright 2018 Thuisoft, Inc. All rights reserved.
 * THUNISOFT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.news.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ErrorPageAct
 * @author hanhw
 * @version 1.0
 *
 */
@Controller
public class ErrorPageAct {

    @RequestMapping("/error/403")
    public String error403(){
        return "error/error403";
    }
}
