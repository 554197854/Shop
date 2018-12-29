package com.shop.controller;

import com.shop.common.EUDataGridResult;
import com.shop.common.ShopMsgResult;
import com.shop.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author N
 * @create 2018/12/27 -- 1:32
 * @email 554197854@qq.com
 */
@Controller
public class ItemParamController {
    @Autowired
    ItemParamService itemParamService;

    @ResponseBody
    @RequestMapping("/item/param/list")
    public EUDataGridResult getItemParamList(Integer page, Integer rows){
        return itemParamService.getItemParamList(page,rows);
    }

    @ResponseBody
    @RequestMapping("/item/param/query/itemcatid/{catId}")
    public ShopMsgResult getItemParam(@PathVariable("catId") long catId){
        return itemParamService.getItemParamByCatId(catId);
    }

    @ResponseBody
    @RequestMapping(value="/item/param/save/{catId}",method = RequestMethod.POST)
    public ShopMsgResult insertItemParam(@PathVariable("catId") long catId, @RequestParam("paramData") String paramData)
    {
        System.out.println(paramData);
        return itemParamService.insertItemParam(catId,paramData);
    }
}
