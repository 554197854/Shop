package com.shop.controller;

import com.shop.common.EUDataGridResult;
import com.shop.common.ShopMsgResult;
import com.shop.pojo.TbItem;
import com.shop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author N
 * @create 2018/12/21 -- 20:19
 * @email 554197854@qq.com
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @ResponseBody
    @RequestMapping(value = "/item/{id}",method = RequestMethod.GET)
    public TbItem getItemById(@PathVariable("id") Integer id){
        return itemService.getItemById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/item/list",method = RequestMethod.GET)
    public EUDataGridResult getItemList(Integer page, Integer rows)
    {
        return itemService.getItemList(page,rows);
    }

    @ResponseBody
    @RequestMapping(value = "/item/save",method = RequestMethod.POST)
    public ShopMsgResult insertTbitem(TbItem tbItem, String desc, String paramData)
    {
       return itemService.insertItem(tbItem,desc,paramData);
    }
}
