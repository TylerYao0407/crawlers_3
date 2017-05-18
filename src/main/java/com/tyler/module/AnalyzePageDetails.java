package com.tyler.module;

import com.tyler.model.DownloadResult;
import com.tyler.model.PageDetails;

/**
 * Created by huyue on 2017/5/18.
 */
public interface AnalyzePageDetails {
    PageDetails analyzePageDetails(DownloadResult dr) throws Exception;
}
