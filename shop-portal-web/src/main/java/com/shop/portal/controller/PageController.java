package com.shop.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author N
 * @create 2018/12/30 -- 15:36
 * @email 554197854@qq.com
 */
@Controller
public class PageController {
    @RequestMapping("/index")
    public  String  showIndex(){
        return "index";
    }
}
