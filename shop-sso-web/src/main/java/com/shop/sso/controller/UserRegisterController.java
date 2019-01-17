package com.shop.sso.controller;

import com.shop.common.ShopMsgResult;
import com.shop.sso.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author N
 * @create 2019/1/15 -- 23:07
 * @email 554197854@qq.com
 */
@Controller
public class UserRegisterController {

    @Autowired
    private UserRegisterService userRegisterService;

    @RequestMapping(value = "/user/check/{param}/{type}",method = RequestMethod.GET)
    @ResponseBody
    public ShopMsgResult checkAccout(@PathVariable("param") String param, @PathVariable("type") Integer type) {
        return userRegisterService.checkData(param, type);


    }
}
