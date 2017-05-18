package com.tyler.start;

import com.tyler.module.AnalyzeUrl;
import com.tyler.module.AnalyzeUrlImpl;

/**
 * Created by huyue on 2017/5/18.
 */
public class AnalyzeUrlTest {
    public static void main(String[] args) {
        AnalyzeUrl au = new AnalyzeUrlImpl();
        System.out.println(au.getDomian("http://down.apps.sina.com.cn/sinasrc/8f/e1/2eb4f1963c19643bde01ead42a44e18f.apk"));
        System.out.println(au.getHomeSite("http://down.apps.sina.com.cn/sinasrc/8f/e1/2eb4f1963c19643bde01ead42a44e18f.apk"));

    }
}
