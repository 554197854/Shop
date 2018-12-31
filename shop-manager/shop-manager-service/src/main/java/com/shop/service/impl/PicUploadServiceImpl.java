package com.shop.service.impl;

import com.shop.common.EUPicUploadResult;
import com.shop.service.PicUploadService;
import com.shop.utils.FastDFSUtils;
import org.csource.common.NameValuePair;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author N
 * @create 2018/12/26 -- 16:00
 * @email 554197854@qq.com
 */
@Service
public class PicUploadServiceImpl implements PicUploadService {
    @Override
    public EUPicUploadResult picUpload(byte[] bytes, String type, NameValuePair[] meta_list) throws Exception {

        EUPicUploadResult euPicUploadResult=new EUPicUploadResult();

        try {
            FastDFSUtils fastDFSUtils = FastDFSUtils.getFastDFS();
            String url = fastDFSUtils.uploadFile(bytes,type,meta_list);
            System.out.println(url);
            euPicUploadResult.setUrl(url);
            euPicUploadResult.setError(0);
            return euPicUploadResult;
        } catch (Exception e) {
            euPicUploadResult.setError(1);
            euPicUploadResult.setMessage("图片上传失败");
            return euPicUploadResult;
        }


    }

}

