package com.tyler.module;

import com.tyler.model.DownloadResult;
import com.tyler.util.UrlMd5;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.tyler.util.UrlMd5.replaceBlank;

/**
 * Created by huyue on 2017/5/18.
 */
public class DownloadPageImpl implements DownloadPage{
    public DownloadResult downloadPage(String url) {
        /**
         * 通过utf-8先默认下载页面
         */
        if(url==null){
            return null;
        }else{
            DownloadResult dr = getHtmlByUrl(replaceBlank(url),"utf-8");
            if(dr.getPage()!=null){
                dr.setCharset("utf-8");
                //因为我们开始不知道页面的编码
                //所以我们需要从已经下载的页面中解析出真正的页面编码
                Document document = Jsoup.parse(dr.getPage());
                Elements metas = document.getElementsByTag("meta");
                for (Element meta : metas) {
                    String content = meta.attr("content");
                    String charset = getCharSet(content);
                    //通常情况下，页面编码可以这么解析出来
                    if(charset!=null&&!charset.equals("")&&!charset.toLowerCase().equals("utf-8")){
                        dr = getHtmlByUrl(replaceBlank(url),charset);
                        dr.setCharset(charset);
                    }
                    //一部分情况下还需要这样
                    String charset1 = meta.attr("charset");
                    if(charset1!=null&&!charset1.equals("")&&!charset1.toLowerCase().equals("utf-8")){
                        dr = getHtmlByUrl(replaceBlank(url),charset1);
                        dr.setCharset(charset1);
                    }
                }
            }
            dr.setUrlMd5(UrlMd5.md5(replaceBlank(url)));
            return dr;
        }

    }
    //使用HttpClient下载页面的实现过程
    private static DownloadResult getHtmlByUrl(String url,String charset){
        DownloadResult dr = new DownloadResult();
        //创建httpClient对象
        CloseableHttpClient httpClient= HttpClients.createDefault();
        //以get方式请求该URL
        HttpGet httpget = new HttpGet(replaceBlank(url));
        //设置超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(5000).setConnectTimeout(5000).setSocketTimeout(5000).build();
        httpget.setConfig(requestConfig);
        try {
            //得到responce对象
            CloseableHttpResponse response = httpClient.execute(httpget);
            //返回码
            int resStatus = response.getStatusLine().getStatusCode();
            dr.setCode(resStatus+"");
            dr.setDownloadTime(new Timestamp(System.currentTimeMillis()));
            dr.setUrl(replaceBlank(url));
            //200正常  其他就不对
            if (resStatus== HttpStatus.SC_OK) {
                //获得相应实体
                HttpEntity entity = response.getEntity();
                if (entity!=null) {
                    //获得html源代码
                    dr.setPage(EntityUtils.toString(entity,charset));
                    dr.setPageMd5(UrlMd5.md5(dr.getPage()));
                }
            }
        } catch (Exception e) {
            System.out.println("访问【"+url+"】出现异常!");
            e.printStackTrace();
        }
        return dr;
    }
    //解析页面编码所使用的正则表达式，返回值为页面的真正编码格式
    private static String getCharSet(String content){
        String regex = ".*charset=([^;]*).*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        if(matcher.find())
            return matcher.group(1);
        else
            return null;
    }
}
