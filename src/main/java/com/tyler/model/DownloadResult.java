package com.tyler.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by huyue on 2017/5/18.
 */
public class DownloadResult implements Serializable{
    //下载的返回的状态码
    private String code;
    //返回页面源代码
    private String page;
    //返回页面源代码的MD5
    private String pageMd5;
    //下载时间
    private Date downloadTime;
    //下载页面的url
    private String url;
    //下载页面的url的MD5
    private String urlMd5;
    //网页编码
    private String charset;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPageMd5() {
        return pageMd5;
    }

    public void setPageMd5(String pageMd5) {
        this.pageMd5 = pageMd5;
    }

    public Date getDownloadTime() {
        return downloadTime;
    }

    public void setDownloadTime(Date downloadTime) {
        this.downloadTime = downloadTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlMd5() {
        return urlMd5;
    }

    public void setUrlMd5(String urlMd5) {
        this.urlMd5 = urlMd5;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    @Override
    public String toString() {
        return "DownloadResult{" +
                "code='" + code + '\'' +
                ", page='" + page + '\'' +
                ", pageMd5='" + pageMd5 + '\'' +
                ", downloadTime=" + downloadTime +
                ", url='" + url + '\'' +
                ", urlMd5='" + urlMd5 + '\'' +
                ", charset='" + charset + '\'' +
                '}';
    }
}
