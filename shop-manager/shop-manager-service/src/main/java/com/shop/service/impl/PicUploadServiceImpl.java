package com.shop.service.impl;

import com.shop.common.EUPicUploadResult;
import com.shop.service.PicUploadService;
import com.shop.utils.FastDFSUtils;
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
    public EUPicUploadResult picUpload(MultipartFile file) throws Exception {
        EUPicUploadResult euPicUploadResult = new EUPicUploadResult();
        if (file == null) {
            euPicUploadResult.setError(1);
            euPicUploadResult.setMessage("图片上传错误");
            return euPicUploadResult;
        }
        String pic_type = file.getOriginalFilename();
        pic_type = pic_type.substring(pic_type.lastIndexOf(".") + 1);
        try {
            FastDFSUtils fastDFSUtils = FastDFSUtils.getFastDFS();
            String url = fastDFSUtils.uploadFile(file.getBytes(), pic_type, null);
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

