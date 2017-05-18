package com.tyler.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by huyue on 2017/5/18.
 */
public class PageDetails implements Serializable{
    //新闻标题
    private String title;
    //新闻内容
    private String content;
    //页面全部文本
    private String text;
    //新闻抓取时间
    private Date intime;
    //保存路径
    private String path;
    //get set

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getIntime() {
        return intime;
    }

    public void setIntime(Date intime) {
        this.intime = intime;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "PageDetails{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", text='" + text + '\'' +
                ", intime=" + intime +
                ", path='" + path + '\'' +
                '}';
    }
}