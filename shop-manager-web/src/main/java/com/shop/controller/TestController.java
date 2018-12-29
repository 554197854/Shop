package com.shop.controller;

import com.shop.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author N
 * @create 2018/12/29 -- 14:06
 * @email 554197854@qq.com
 */
@Controller
public class TestController {
    @Autowired
    TestService testService;

    @ResponseBody
    @RequestMapping("/test")
    public String Test(){
       return testService.getTime();
    }
}
