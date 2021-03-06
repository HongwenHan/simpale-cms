/*
 * @(#)RequestUtils.java 2018年4月22日下午1:26:16
 * news
 * Copyright 2018 Thuisoft, Inc. All rights reserved.
 * THUNISOFT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.news.util;

import static com.news.util.Constants.POST;
import static com.news.util.Constants.UTF8;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
/**
 * RequestUtils
 * @author hhong
 * @version 1.0
 *
 */
public class RequestUtils {
    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory
            .getLogger(RequestUtils.class);

    /**
     * 获取QueryString的参数，并使用URLDecoder以UTF-8格式转码。如果请求是以post方法提交的， 那么将通过HttpServletRequest#getParameter获取。.
     * @param request web请求
     * @param name    参数名称
     * @return the query param
     */
    public static String getQueryParam(HttpServletRequest request,
                                       String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        if (request.getMethod().equalsIgnoreCase(POST)) {
            return request.getParameter(name);
        }
        String s = request.getQueryString();
        if (StringUtils.isBlank(s)) {
            return null;
        }
        try {
            s = URLDecoder.decode(s, UTF8);
        } catch (UnsupportedEncodingException e) {
            LOG.error("encoding " + UTF8 + " not support?", e);
        }
        String[] values = parseQueryString(s).get(name);
        if (values != null && values.length > 0) {
            return values[values.length - 1];
        } else {
            return null;
        }
    }

    /**
     * Gets the query params.
     * @param request the request
     * @return the query params
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getQueryParams(
            HttpServletRequest request) {
        Map<String, String[]> map;
        if (request.getMethod().equalsIgnoreCase(POST)) {
            map = request.getParameterMap();
        } else {
            String s = request.getQueryString();
            if (StringUtils.isBlank(s)) {
                return new HashMap<String, Object>();
            }
            try {
                s = URLDecoder.decode(s, UTF8);
            } catch (UnsupportedEncodingException e) {
                LOG.error("encoding " + UTF8 + " not support?", e);
            }
            map = parseQueryString(s);
        }

        Map<String, Object> params = new HashMap<String, Object>(map.size());
        int len;
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            len = entry.getValue().length;
            if (len == 1) {
                params.put(entry.getKey(), entry.getValue()[0]);
            } else if (len > 1) {
                params.put(entry.getKey(), entry.getValue());
            }
        }
        return params;
    }

    /**
     * Parses a query string passed from the client to the server and builds a <code>HashTable</code> object with
     * key-value pairs. The query string should be in the form of a string packaged by the GET or POST method, that is,
     * it should have key-value pairs in the form <i>key=value</i>, with each pair separated from the next by a &amp;
     * character.
     * <p>
     * <p>
     * A key can appear more than once in the query string with different values. However, the key appears only once in
     * the hashtable, with its value being an array of strings containing the multiple values sent by the query string.
     * <p>
     * <p>
     * The keys and values in the hashtable are stored in their decoded form, so any + characters are converted to
     * spaces, and characters sent in hexadecimal notation (like <i>%xx</i>) are converted to ASCII characters.
     * @param s a string containing the query to be parsed
     * @return a <code>HashTable</code> object built from the parsed key-value pairs
     */
    public static Map<String, String[]> parseQueryString(String s) {
        String[] valArray = null;
        if (s == null) {
            throw new IllegalArgumentException();
        }
        Map<String, String[]> ht = new HashMap<String, String[]>();
        StringTokenizer st = new StringTokenizer(s, "&");
        while (st.hasMoreTokens()) {
            String pair = st.nextToken();
            int pos = pair.indexOf('=');
            if (pos == -1) {
                continue;
            }
            String key = pair.substring(0, pos);
            String val = pair.substring(pos + 1, pair.length());
            if (ht.containsKey(key)) {
                String[] oldVals = ht.get(key);
                valArray = new String[oldVals.length + 1];
                System.arraycopy(oldVals, 0, valArray, 0, oldVals.length);
                valArray[oldVals.length] = val;
            } else {
                valArray = new String[1];
                valArray[0] = val;
            }
            ht.put(key, valArray);
        }
        return ht;
    }

    /**
     * Gets the request map.
     * @param request the request
     * @param prefix  the prefix
     * @return the request map
     */
    public static Map<String, String> getRequestMap(HttpServletRequest request,
                                                    String prefix) {
        return getRequestMap(request, prefix, false);
    }

    /**
     * Gets the request map with prefix.
     * @param request the request
     * @param prefix  the prefix
     * @return the request map with prefix
     */
    public static Map<String, String> getRequestMapWithPrefix(
            HttpServletRequest request, String prefix) {
        return getRequestMap(request, prefix, true);
    }

    /**
     * Gets the request map.
     * @param request        the request
     * @param prefix         the prefix
     * @param nameWithPrefix the name with prefix
     * @return the request map
     */
    @SuppressWarnings("unchecked")
    private static Map<String, String> getRequestMap(HttpServletRequest request,
                                                     String prefix, boolean nameWithPrefix) {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration<String> names = request.getParameterNames();
        String name, key, value;
        while (names.hasMoreElements()) {
            name = names.nextElement();
            if (name.startsWith(prefix)) {
                key = nameWithPrefix ? name : name.substring(prefix.length());
                value = StringUtils.join(request.getParameterValues(name), ',');
                map.put(key, value);
            }
        }
        return map;
    }

    /**
     * 获取访问者IP
     * <p>
     * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
     * <p>
     * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)， 如果还不存在则调用Request .getRemoteAddr()。
     * @param request the request
     * @return the ip addr
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            if (ip.contains("../") || ip.contains("..\\")) {
                return "";
            }
            return ip;
        }
        return getForwardIpAddr(request);

    }

    /**
     * 获取多次反向代理获得的IP
     * @param request the request
     */
    private static String getForwardIpAddr(HttpServletRequest request) {
        String ip;
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                ip = ip.substring(0, index);
            }
            if (ip.contains("../") || ip.contains("..\\")) {
                return "";
            }
            return ip;
        } else {
            ip = request.getRemoteAddr();
            if (ip.contains("../") || ip.contains("..\\")) {
                return "";
            }
            return ip;
        }
    }

    /**
     * 获得当的访问路径
     * <p>
     * HttpServletRequest.getRequestURL+"?"+HttpServletRequest.getQueryString
     * @param request the request
     * @return the location
     */
    public static String getLocation(HttpServletRequest request) {
        UrlPathHelper helper = new UrlPathHelper();
        StringBuffer buff = request.getRequestURL();
        String uri = request.getRequestURI();
        String origUri = helper.getOriginatingRequestUri(request);
        buff.replace(buff.length() - uri.length(), buff.length(), origUri);
        String queryString = helper.getOriginatingQueryString(request);
        if (queryString != null) {
            buff.append("?").append(queryString);
        }
        return buff.toString();
    }

}
