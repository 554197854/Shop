package com.shop.item.controller;

import com.shop.item.pojo.CTitem;
import com.shop.pojo.TbItem;
import com.shop.pojo.TbItemDesc;
import com.shop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author N
 * @create 2019/1/10 -- 18:25
 * @email 554197854@qq.com
 */
@Controller
public class CTitemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    public String getCTitem(@PathVariable("itemId") Long itemId, Model model){
        TbItem tbItem = itemService.getItemById(itemId);
        TbItemDesc itemDesc = itemService.getItemDescById(itemId);
        CTitem item = new CTitem(tbItem);
        System.out.println(item.getPrice());
        model.addAttribute("item",item);
        model.addAttribute("itemDesc",itemDesc);
        return "item";

    }

}
