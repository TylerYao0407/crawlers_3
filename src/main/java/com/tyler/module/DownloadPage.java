package com.tyler.module;

import com.tyler.model.DownloadResult;

/**
 * Created by huyue on 2017/5/18.
 */
public interface DownloadPage {
    DownloadResult downloadPage(String url);
}
