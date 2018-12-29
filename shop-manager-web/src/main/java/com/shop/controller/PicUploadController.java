package com.shop.controller;

import com.shop.service.PicUploadService;
import com.shop.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    PicUploadService picUploadService;

    @ResponseBody
    @RequestMapping("/pic/upload")
    public String picUpload(MultipartFile uploadFile) throws Exception{
        //手动使用JsonUtils工具将对象转化为JSON字符串String类型，来适应浏览器对JSON数据类型兼容性问题
        return JsonUtils.objectToJson(picUploadService.picUpload(uploadFile));
    }
}
