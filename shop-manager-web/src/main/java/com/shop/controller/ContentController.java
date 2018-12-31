package com.shop.controller;

import com.shop.common.EUDataGridResult;
import com.shop.common.ShopMsgResult;
import com.shop.content.service.ContentService;
import com.shop.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author N
 * @create 2018/12/31 -- 15:30
 * @email 554197854@qq.com
 */
@Controller
public class ContentController {

    @Autowired
    private ContentService contentService;
    @ResponseBody
    @RequestMapping("/content/query/list")
    public EUDataGridResult getContentList(long categoryId,Integer page,Integer rows){
        return  contentService.getContentList(categoryId,page,rows);
    }

    @ResponseBody
    @RequestMapping("/content/save")
    public ShopMsgResult saveContent(TbContent tbContent){
        return contentService.saveContent(tbContent);
    }
}
