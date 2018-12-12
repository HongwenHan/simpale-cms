/*
 * 
 */
package com.news.util;

import org.apache.commons.lang.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.lexer.Lexer;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.util.ParserException;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.regex.Pattern;


/**
 * 字符串的帮助类，提供静态方法，不可以实例化。.
 */
public final class StrUtils {

    /**
     * 禁止实例化.
     */
    private StrUtils() {
    }

    /**
     * 处理url
     * <p>
     * url为null返回null，url为空串或以http://或https://开头，则加上http://，其他情况返回原参数。.
     * @param url the url
     * @return the string
     */
    public static String handelUrl(String url) {
        if (url == null) {
            return null;
        }
        url = url.trim();
        if (url.equals("") || url.startsWith("http://")
                || url.startsWith("https://")) {
            return url;
        } else {
            return "http://" + url.trim();
        }
    }

    /**
     * 分割并且去除空格.
     * @param str  待分割字符串
     * @param sep  分割符
     * @param sep2 第二个分隔符
     * @return 如果str为空，则返回null。
     */
    public static String[] splitAndTrim(String str, String sep, String sep2) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        if (!StringUtils.isBlank(sep2)) {
            str = StringUtils.replace(str, sep2, sep);
        }
        String[] arr = StringUtils.split(str, sep);
        // trim
        for (int i = 0, len = arr.length; i < len; i++) {
            arr[i] = arr[i].trim();
        }
        return arr;
    }

    /**
     * 文本转html.
     * @param txt the txt
     * @return the string
     */
    public static String txt2htm(String txt) {
        if (StringUtils.isBlank(txt)) {
            return txt;
        }
        final float radius = 1.2F;
        StringBuilder sb = new StringBuilder((int) (txt.length() * radius));
        char c;
        boolean doub = false;
        for (int i = 0; i < txt.length(); i++) {
            c = txt.charAt(i);
            if (c == ' ') {
                if (doub) {
                    sb.append(' ');
                    doub = false;
                } else {
                    sb.append("&nbsp;");
                    doub = true;
                }
            } else {
                doub = false;
                switch (c) {
                    case '&':
                        sb.append("&amp;");
                        break;
                    case '<':
                        sb.append("&lt;");
                        break;
                    case '>':
                        sb.append("&gt;");
                        break;
                    case '"':
                        sb.append("&quot;");
                        break;
                    case '\n':
                        sb.append("<br/>");
                        break;
                    default:
                        sb.append(c);
                        break;
                }
            }
        }
        return sb.toString();
    }

    /**
     * 剪切文本。如果进行了剪切，则在文本后加上"..."
     * @param s      剪切对象。
     * @param len    编码小于256的作为一个字符，大于256的作为两个字符。
     * @param append the append
     * @return the string
     */
    public static String textCut(String s, int len, String append) {
        if (s == null) {
            return null;
        }
        int slen = s.length();
        if (slen <= len) {
            return s;
        }
        // 编码小于256的作为一个字符，大于256的作为两个字符。
        final int wordMinLen = 256;
        // 最大计数（如果全是英文）
        int maxCount = len * 2;
        int count = 0;
        int i = 0;
        for (; count < maxCount && i < slen; i++) {
            if (s.codePointAt(i) < wordMinLen) {
                count++;
            } else {
                count += 2;
            }
        }
        if (i < slen) {
            if (count > maxCount) {
                i--;
            }
            if (!StringUtils.isBlank(append)) {
                if (s.codePointAt(i - 1) < wordMinLen) {
                    i -= 2;
                } else {
                    i--;
                }
                return s.substring(0, i) + append;
            } else {
                return s.substring(0, i);
            }
        } else {
            return s;
        }
    }

    /**
     * Html cut.
     * @param s      the s
     * @param len    the len
     * @param append the append
     * @return the string
     */
    public static String htmlCut(String s, int len, String append) {
        String text = html2Text(s, len * 2);
        return textCut(text, len, append);
    }

    /**
     * Html2 text.
     * @param html the html
     * @param len  the len
     * @return the string
     */
    public static String html2Text(String html, int len) {
        try {
            Lexer lexer = new Lexer(html);
            Node node;
            StringBuilder sb = new StringBuilder(html.length());
            while ((node = lexer.nextNode()) != null) {
                if (node instanceof TextNode) {
                    sb.append(node.toHtml());
                }
                if (sb.length() > len) {
                    break;
                }
            }
            return sb.toString();
        } catch (ParserException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the keywords.
     * @param keyword 源词汇
     * @param smart   是否智能分词
     * @return 分词词组(, 拼接)
     */
    public static String getKeywords(String keyword, boolean smart) throws IOException {
        StringReader reader = new StringReader(keyword);
        IKSegmenter iks = new IKSegmenter(reader, smart);
        StringBuilder buffer = new StringBuilder();
        Lexeme lexeme;
        while ((lexeme = iks.next()) != null) {
            buffer.append(lexeme.getLexemeText()).append(',');
        }
        //去除最后一个,
        if (buffer.length() > 0) {
            buffer.setLength(buffer.length() - 1);
        }
        return buffer.toString();
    }


    /**
     * 检查字符串中是否包含被搜索的字符串。被搜索的字符串可以使用通配符'*'。.
     * @param str    the str
     * @param search the search
     * @return true, if successful
     */
    public static boolean contains(String str, String search) {
        if (StringUtils.isBlank(str) || StringUtils.isBlank(search)) {
            return false;
        }
        String reg = StringUtils.replace(search, "*", ".*");
        Pattern p = Pattern.compile(reg);
        return p.matcher(str).matches();
    }

    /**
     * Contains key string.
     * @param str the str
     * @return true, if successful
     */
    public static boolean containsKeyString(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        final String[] containsArr = {"'", "\"", "\r", "\n", "\t", "\b", "\f"};
        for (String s : containsArr) {
            if (str.contains(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds the char for string.
     * @param str       the str
     * @param strLength the str length
     * @param c         the c
     * @param position  the position
     * @return the string
     */
    public static String addCharForString(String str, int strLength, char c,
                                          int position) {
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuilder sb = new StringBuilder();
                if (position == 1) {
                    //右補充字符c
                    sb.append(c).append(str);
                } else {
                    //左補充字符c
                    sb.append(str).append(c);
                }
                str = sb.toString();
                strLen = str.length();
            }
        }
        return str;
    }

    // 将""和'转义

    /**
     * Replace key string.
     * @param str the str
     * @return the string
     */
    public static String replaceKeyString(String str) {
        if (containsKeyString(str)) {
            return str.replace("'", "\\'").replace("\"", "\\\"")
                    .replace("\r", "\\r").replace("\n", "\\n")
                    .replace("\t", "\\t").replace("\b", "\\b")
                    .replace("\f", "\\f");
        } else {
            return str;
        }
    }

    //单引号转化成双引号

    /**
     * Replace string.
     * @param str the str
     * @return the string
     */
    public static String replaceString(String str) {
        if (containsKeyString(str)) {
            return str.replace("'", "\"").replace("\"", "\\\"")
                    .replace("\r", "\\r").replace("\n", "\\n")
                    .replace("\t", "\\t").replace("\b", "\\b")
                    .replace("\f", "\\f");
        } else {
            return str;
        }
    }

    /**
     * Gets the suffix.
     * @param str the str
     * @return the suffix
     */
    public static String getSuffix(String str) {
        int splitIndex = str.lastIndexOf(".");
        return str.substring(splitIndex + 1);
    }


}
