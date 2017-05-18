package com.tyler.start;

import com.tyler.model.DownloadResult;
import com.tyler.model.Links;
import com.tyler.module.AnalyzePageLinks;
import com.tyler.module.AnalyzePageLinksImpl;
import com.tyler.module.DownloadPage;
import com.tyler.module.DownloadPageImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huyue on 2017/5/18.
 */
public class AnalyzePageLinksTest {
    public static void main(String[] args) {
        DownloadResult dr = new DownloadResult();
        DownloadPage dp = new DownloadPageImpl();
        dr = dp.downloadPage("http://www.sohu.com/");
        AnalyzePageLinks analyzePageLinks = new AnalyzePageLinksImpl();
        Links l = analyzePageLinks.analyzePageLinks(dr.getPage(),dr.getUrl());
        List<String> links= l.getInLinks();
        for(int i = 0;i < links.size();i++){
            System.out.println(links.get(i));
        }
        Map<String,List<String>> m = new HashMap<String, List<String>>();
        m = l.getContainMap();
        System.out.println(m.toString());
    }

}
