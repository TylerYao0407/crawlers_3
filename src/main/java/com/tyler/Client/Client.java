package com.tyler.Client;

import com.tyler.model.DownloadResult;
import com.tyler.module.AnalyzePageDetails;
import com.tyler.module.AnalyzePageLinks;
import com.tyler.module.AnalyzeUrl;
import com.tyler.module.DownloadPage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by huyue on 2017/5/18.
 */
public class Client {
    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:conf/Client.xml");
        DownloadPage dp=(DownloadPage)ctx.getBean("downloadPageService");
        AnalyzeUrl au = (AnalyzeUrl)ctx.getBean("analyzeUrlService") ;
        AnalyzePageLinks apl = (AnalyzePageLinks)ctx.getBean("analyzePageLinksService");
        AnalyzePageDetails apd = (AnalyzePageDetails)ctx.getBean("analyzePageDetailsService");
        DownloadResult dr = dp.downloadPage("http://news.sina.com.cn/gov/xlxw/2017-05-18/doc-ifyfkqiv6488311.shtml");
        System.out.println(apd.analyzePageDetails(dr).toString());

    }
}
