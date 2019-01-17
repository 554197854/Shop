package com.shop.sso.service;

import com.shop.common.ShopMsgResult;
import com.shop.pojo.TbUser;

/**
 * @author N
 * @create 2019/1/15 -- 20:32
 * @email 554197854@qq.com
 */
public interface UserRegisterService {

     ShopMsgResult checkData(String param,Integer type);

     ShopMsgResult userRegister(TbUser tbUser);
}
