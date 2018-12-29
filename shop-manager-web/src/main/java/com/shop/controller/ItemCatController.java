package com.shop.controller;

import com.shop.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author N
 * @create 2018/12/23 -- 0:08
 * @email 554197854@qq.com
 */
@Controller
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    @ResponseBody
    @RequestMapping(value = "/item/cat/list",method = RequestMethod.POST)
    public List getItemCatList(@RequestParam(value="id", defaultValue="0")Long parentId){
       return itemCatService.getItemCatList(parentId);
    }
}
