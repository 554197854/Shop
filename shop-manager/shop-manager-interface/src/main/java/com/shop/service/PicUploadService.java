package com.shop.service;

import com.shop.common.EUPicUploadResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author N
 * @create 2018/12/26 -- 15:58
 * @email 554197854@qq.com
 */
public interface PicUploadService  {
    EUPicUploadResult picUpload(MultipartFile file) throws Exception;
}
