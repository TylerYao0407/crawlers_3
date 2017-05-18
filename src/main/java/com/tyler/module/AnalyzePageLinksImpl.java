package com.tyler.module;

import com.tyler.model.Links;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huyue on 2017/5/18.
 */
public class AnalyzePageLinksImpl implements AnalyzePageLinks {
    public Links analyzePageLinks(String page, String url) {
        Links l = new Links();
        List<String> inLinks = new ArrayList<String>();
        Map<String,List<String>> containMap = new HashMap<String, List<String>>();
        List<String> outLinks = new ArrayList<String>();
        String ss = new AnalyzeUrlImpl().getDomian(url);
        String sitename = ss.substring(0,ss.length()-4);
        String regex = "a[href~=^(https?:\\/\\/)?.*.(.*)$]";
        String linkHref = null;
        Elements links = Jsoup.parse(page).select(regex);
        for (Element link : links) {
            linkHref = link.attr("href");
            if ((linkHref.indexOf(sitename) != -1) && (linkHref.indexOf("/") != -1)){
                inLinks.add(getAbsoluteURL(url,linkHref.trim()));
            }else if((linkHref.indexOf(sitename) == -1) && (linkHref.indexOf("/") != -1)&&(linkHref.trim().length()!=1)){
                outLinks.add(getAbsoluteURL(url,linkHref.trim()));
            }else{
                //System.out.println(linkHref);
            }
        }
        l.setInLinks(inLinks);
        containMap.put(url,outLinks);
        l.setContainMap(containMap);
        return l;
    }
    public static String getAbsoluteURL(String baseURI, String relativePath){
        String abURL=null;
        try {
            URI base=new URI(baseURI);//基本网页URI
            URI abs=base.resolve(relativePath);//解析于上述网页的相对URL，得到绝对URI
            URL absURL=abs.toURL();//转成URL
            //System.out.println(absURL);
            abURL = absURL.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally{
            return abURL;
        }
    }
}
