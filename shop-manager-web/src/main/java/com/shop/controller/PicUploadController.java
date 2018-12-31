package com.shop.controller;

import com.shop.common.EUDataGridResult;
import com.shop.common.EUPicUploadResult;
import com.shop.service.PicUploadService;
import com.shop.utils.FastDFSUtils;
import com.shop.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author N
 * @create 2018/12/26 -- 16:40
 * @email 554197854@qq.com
 */
@Controller
public class PicUploadController {

    @Autowired
    private PicUploadService picUploadService;

    @ResponseBody
    @RequestMapping(value = "/pic/upload",method = RequestMethod.POST,produces= MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
    public String picUpload(MultipartFile uploadFile) throws Exception {

        if (uploadFile == null) {

            EUPicUploadResult euPicUploadResult = new EUPicUploadResult();
            euPicUploadResult.setError(1);
            euPicUploadResult.setMessage("图片上传错误");
            return JsonUtils.objectToJson(euPicUploadResult);
        }else{

            String pic_type = uploadFile.getOriginalFilename();
            pic_type = pic_type.substring(pic_type.lastIndexOf(".") + 1);
            return JsonUtils.objectToJson(picUploadService.picUpload(uploadFile.getBytes(), pic_type, null));
        }
        //手动使用JsonUtils工具将对象转化为JSON字符串String类型，来适应浏览器对JSON数据类型兼容性问题


    }



//    @ResponseBody
//    @RequestMapping(value = "/pic/upload",method = RequestMethod.POST,produces= MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
//    public String picUpload(MultipartFile uploadFile) throws Exception {
//        System.out.println("upload");
//        //手动使用JsonUtils工具将对象转化为JSON字符串String类型，来适应浏览器对JSON数据类型兼容性问题
//
//        System.out.println("uploadservice");
//        EUPicUploadResult euPicUploadResult = new EUPicUploadResult();
//        if (uploadFile == null) {
//            euPicUploadResult.setError(1);
//            euPicUploadResult.setMessage("图片上传错误");
//            return JsonUtils.objectToJson(euPicUploadResult);
//        }
//        String pic_type = uploadFile.getOriginalFilename();
//        pic_type = pic_type.substring(pic_type.lastIndexOf(".") + 1);
//        try {
//            FastDFSUtils fastDFSUtils = FastDFSUtils.getFastDFS();
//            String url = fastDFSUtils.uploadFile(uploadFile.getBytes(), pic_type, null);
//            euPicUploadResult.setUrl(url);
//            euPicUploadResult.setError(0);
//            return JsonUtils.objectToJson(euPicUploadResult);
//        } catch (Exception e) {
//            euPicUploadResult.setError(1);
//            euPicUploadResult.setMessage("图片上传失败");
//            return JsonUtils.objectToJson(euPicUploadResult);
//        }
//
//
//
//    }
}


