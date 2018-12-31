package com.shop.service;

import com.shop.common.EUPicUploadResult;
import org.csource.common.NameValuePair;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author N
 * @create 2018/12/26 -- 15:58
 * @email 554197854@qq.com
 */
public interface PicUploadService  {
    EUPicUploadResult picUpload(byte[] bytes, String type, NameValuePair[] meta_list) throws Exception;
}
