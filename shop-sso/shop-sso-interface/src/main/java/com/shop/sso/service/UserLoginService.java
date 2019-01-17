package com.shop.sso.service;

import com.shop.common.ShopMsgResult;

/**
 * @author N
 * @create 2019/1/16 -- 17:42
 * @email 554197854@qq.com
 */
public interface UserLoginService {
    ShopMsgResult login(String username,String password);

    ShopMsgResult getUserByToken(String token);

    ShopMsgResult loginOut(String token);
}
