package com.shop.controller;

import com.shop.common.EUTreeNode;
import com.shop.common.ShopMsgResult;
import com.shop.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author N
 * @create 2018/12/30 -- 21:18
 * @email 554197854@qq.com
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
    @Autowired
    ContentCategoryService contentCategoryService;

    @ResponseBody
    @RequestMapping("/list")
    public List getContentCategorylist(@RequestParam(value = "id",defaultValue = "0") Long parentId){
        return contentCategoryService.getContentCategoryList(parentId);
    }

    @ResponseBody
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ShopMsgResult createContentCategory(@RequestParam(value = "parentId")Long parentId,@RequestParam(value = "name") String name){
        return contentCategoryService.createContentCategory(parentId,name);
    }
}
