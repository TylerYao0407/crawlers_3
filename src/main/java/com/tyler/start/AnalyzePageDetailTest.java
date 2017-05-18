package com.tyler.start;

import com.tyler.model.DownloadResult;
import com.tyler.module.AnalyzePageDetails;
import com.tyler.module.AnalyzePageDetailsImpl;
import com.tyler.module.DownloadPage;
import com.tyler.module.DownloadPageImpl;

/**
 * Created by huyue on 2017/5/18.
 */
public class AnalyzePageDetailTest {

    public static void main(String[] args) throws Exception {
        DownloadResult dr = new DownloadResult();
        DownloadPage dp = new DownloadPageImpl();
        dr = dp.downloadPage("http://news.sina.com.cn/gov/xlxw/2017-05-18/doc-ifyfkqks4258771.shtml");
        AnalyzePageDetails analyzePageDetails = new AnalyzePageDetailsImpl();
        System.out.println(analyzePageDetails.analyzePageDetails(dr));
    }
}
