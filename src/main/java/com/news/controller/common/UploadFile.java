/*
 * @(#)UploadFile.java 2018年6月16日下午10:24:26
 * news
 * Copyright 2018 Thuisoft, Inc. All rights reserved.
 * THUNISOFT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.news.controller.common;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * UploadFile
 * @author hhong
 * @version 1.0
 *
 */
@Controller
public class UploadFile {

    @RequestMapping(value="uploadFile")
    @ResponseBody
    public String uploadFile(MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        String context = request.getContextPath();
        JSONObject json = new JSONObject();
        if(file.isEmpty()){  
            return "false";  
        }  
        String fileName = file.getOriginalFilename();  
          
        /*String path = System.getProperty("user.dir") + "/uploadFile" ;*/
        String path = "D:/news/uploadFile" ;
        File dest = new File(path + "/" + fileName);  
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在  
            dest.getParentFile().mkdir();  
        }
        //获取相对路径
        try { 
            String filePath = dest.getPath();
            String relativePath = context + filePath.substring(filePath.indexOf("\\uploadFile"));
            file.transferTo(dest); //保存文件 
            json.put("success", true);
            json.put("path", relativePath);
            return json.toString();  
        } catch (IllegalStateException e) {  
            e.printStackTrace();  
            json.put("success", false);
            return json.toString();  
        } catch (IOException e) {  
            e.printStackTrace();  
            json.put("success", false);
            return json.toString();  
        } 
    }
}
