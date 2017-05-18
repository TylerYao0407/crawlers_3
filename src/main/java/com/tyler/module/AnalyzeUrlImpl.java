package com.tyler.module;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huyue on 2017/5/18.
 */
public class AnalyzeUrlImpl implements AnalyzeUrl {
    public String getHomeSite(String url) {
        Pattern top = Pattern.compile("http(s)?://", Pattern.CASE_INSENSITIVE);
        Matcher matcher1 = top.matcher(url);
        matcher1.find();
        String t = matcher1.group();
        Pattern p = Pattern.compile("(?<=http(s?)://|\\.)[^.]*?\\.(com\\.cn|net\\.cn|org\\.cn|com|net|org|cn|biz|info|cc|tv)/", Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(url);
        matcher.find();
        return t+matcher.group();
    }

    public String getDomian(String url) {
        Pattern p = Pattern.compile("(?<=http(s?)://|\\.)[^.]*?\\.(com\\.cn|net\\.cn|org\\.cn|com|net|org|cn|biz|info|cc|tv)/", Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(url);
        matcher.find();
        return matcher.group();
    }
}
