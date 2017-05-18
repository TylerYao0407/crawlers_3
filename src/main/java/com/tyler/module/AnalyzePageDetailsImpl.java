package com.tyler.module;

import com.tyler.model.DownloadResult;
import com.tyler.model.PageDetails;
import com.tyler.util.UrlMd5;
import htmlbot.contentextractor.ContentExtractor;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlparser.Parser;
import org.htmlparser.beans.StringBean;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by huyue on 2017/5/18.
 */
public class AnalyzePageDetailsImpl implements AnalyzePageDetails {
    public PageDetails analyzePageDetails(DownloadResult downloadResult) throws Exception {
        PageDetails pageDetails = new PageDetails();
        HtmlCleaner cleaner = new HtmlCleaner();
        TagNode node = cleaner.clean(downloadResult.getPage());
        //使用XPath解析新闻的标题
        Object[] title = node.evaluateXPath("//title/text()");
        pageDetails.setTitle(title[0].toString().trim().replace("\n", ""));
        String content = ContentExtractor.getContentByHtml(downloadResult.getPage());
        pageDetails.setContent(content);
        pageDetails.setIntime(new Date(System.currentTimeMillis()));
        pageDetails.setText(getTextByPage(downloadResult.getPage()));
        Calendar calendar = Calendar.getInstance();
        String suffix = downloadResult.getUrl().substring(downloadResult.getUrl().lastIndexOf("."));
        String urlMd5 = UrlMd5.md5(downloadResult.getUrl());
        String pageMd5 = UrlMd5.md5(downloadResult.getPage());
        String path = "/" + calendar.get(Calendar.YEAR) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + UrlMd5.md5(urlMd5+pageMd5) + suffix;
        pageDetails.setPath(path);
        return pageDetails;
    }
    public static String getTextByPage(String page) throws  Exception {
        page = page.replaceAll("<script[^>]*?>[\\s\\S]*?</script>", "");
        Parser parser = new Parser();
        parser.setInputHTML(page);
        StringBean sb = new StringBean();
        //设置不需要得到页面所包含的链接信息
        sb.setLinks(false);
        //设置将不间断空格由正规空格所替代
        sb.setReplaceNonBreakingSpaces(true);
        //设置将一序列空格由一个单一空格所代替
        sb.setCollapse(true);
        parser.visitAllNodesWith(sb);
        //返回解析后的网页纯文本信息
        return sb.getStrings();
    }
}
