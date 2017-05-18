package com.tyler.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by huyue on 2017/5/18.
 */
public class Links implements Serializable{
    //内链列表
    private List<String> inLinks;
    //外链列表，Map中存的是本网站url和外链url列表
    private Map<String,List<String>> containMap;

    public List<String> getInLinks() {
        return inLinks;
    }

    public void setInLinks(List<String> inLinks) {
        this.inLinks = inLinks;
    }

    public Map<String, List<String>> getContainMap() {
        return containMap;
    }

    public void setContainMap(Map<String, List<String>> containMap) {
        this.containMap = containMap;
    }

    @Override
    public String toString() {
        return "Links{" +
                "inLinks=" + inLinks +
                ", containMap=" + containMap +
                '}';
    }
}