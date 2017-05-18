package com.tyler.util;

import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by tyler on 2017/4/19.
 */
public class UrlMd5 {
    public static void main(String[] args) throws ParseException {
        //84675a6817fc8715e33bc1c631154b5d
        System.out.println(md5("http://service.weibo.com/share/share.php?url=http://blog.sina.com.cn/s/blog_15f20fe440102wyfs.html?tj=1&title=??????? ??????????????????- &appkey=false&ralateUid=1231759973&searchPic=true"));
        System.out.println(md5("http://www.sina.com.cn/"));
        String s = "2017-05-06";
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date maxD = format1.parse(s);
        System.out.println(maxD);
        System.out.println(format1.parse(format1.format(new Date())));
        System.out.println(maxD.before(format1.parse(format1.format(new Date()))));
    }
    public static String md5(String string) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
                'e', 'f' };
        try {
            byte[] bytes = string.trim().getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            byte[] updateBytes = messageDigest.digest();
            int len = updateBytes.length;
            char myChar[] = new char[len * 2];
            int k = 0;
            for (int i = 0; i < len; i++) {
                byte byte0 = updateBytes[i];
                myChar[k++] = hexDigits[byte0 >>> 4 & 0x0f];
                myChar[k++] = hexDigits[byte0 & 0x0f];
            }
            return new String(myChar);
        } catch (Exception e) {
            return null;
        }
    }
    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}
