package com.tyler.module;

import com.tyler.model.DownloadResult;
import com.tyler.util.UrlMd5;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by huyue on 2017/5/18.
 */
public class SaveToLocalImpl implements SaveToLocal {
    public String saveToLocal(DownloadResult downloadResult) {
        Date date = downloadResult.getDownloadTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //获取当前系统文件分隔符
        String separator = File.separator;
        //获取文件的后缀名
        String suffix = downloadResult.getUrl().substring(downloadResult.getUrl().lastIndexOf("."));
        //拼接得到下载结果的地址
        //TODO 在实际生产中需要修改位置
        String path = "D:" + separator + calendar.get(Calendar.YEAR) + separator + (calendar.get(Calendar.MONTH) + 1) + separator + calendar.get(Calendar.DAY_OF_MONTH) + separator;
        String urlMd5 = UrlMd5.md5(downloadResult.getUrl());
        String pageMd5 = UrlMd5.md5(downloadResult.getPage());
        //拼接获得文件名
        String filename = UrlMd5.md5(urlMd5+pageMd5) + suffix;
        //文件的绝对路径
        String absPath = path + filename;
        //使用流的形式写到本地
        String content = downloadResult.getPage();
        Scanner input = new Scanner(content + "\n");
        File file = new File(path);
        FileOutputStream fos = null;
        try {
            if (!file.exists()) {
                file.mkdirs();
            }
            fos = new FileOutputStream(new File(absPath));
            while (input.hasNext()) {
                String a = input.next();
                fos.write((a + "\r\n").getBytes(downloadResult.getCharset()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return absPath;
    }
}
