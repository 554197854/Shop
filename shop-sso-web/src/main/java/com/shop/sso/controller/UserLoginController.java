package com.shop.sso.controller;

import com.shop.common.ShopMsgResult;
import com.shop.sso.service.UserLoginService;
import com.shop.utils.CookieUtils;
import com.shop.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author N
 * @create 2019/1/16 -- 21:38
 * @email 554197854@qq.com
 */
@Controller
public class UserLoginController {
    @Autowired
    private UserLoginService userLoginService;

    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @ResponseBody
    public ShopMsgResult login(String username, String password, HttpServletRequest request, HttpServletResponse response){
        ShopMsgResult login = userLoginService.login(username, password);
        Object data = login.getData();
        CookieUtils.setCookie(request,response,"User_T",login.getData().toString());
        return login;
    }


    @RequestMapping(value = "/user/token/{token}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    @ResponseBody
    public Object getUserByToken(@PathVariable String token,@PathVariable  String callback){
        ShopMsgResult userByToken = userLoginService.getUserByToken(token);
        if(StringUtils.isNotBlank(callback)){
            MappingJacksonValue value = new MappingJacksonValue(userByToken);
            value.setJsonpFunction(callback);
            return value;
        }
        return userByToken;
    }

    @RequestMapping(value = "/user/token/{token}",method = RequestMethod.GET)
    @ResponseBody
    public ShopMsgResult loginOut(@PathVariable String token){
        return userLoginService.getUserByToken(token);
    }
}
