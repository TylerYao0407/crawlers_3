package com.tyler.start;

import com.tyler.model.DownloadResult;
import com.tyler.module.DownloadPage;
import com.tyler.module.DownloadPageImpl;

/**
 * Created by huyue on 2017/5/18.
 */
public class DownloadTest {


    public static void main(String[] args) {
        DownloadResult dr = new DownloadResult();
        DownloadPage dp = new DownloadPageImpl();
        dr = dp.downloadPage("http://www.baidu.com/");
        System.out.println(dr.getCode());
        System.out.println(dr.getDownloadTime());
        System.out.println(dr.getCharset());
        System.out.println(dr.getPage());
        System.out.println(dr.getPageMd5());
        System.out.println(dr.getUrl());
        System.out.println(dr.getUrlMd5());

    }
}
