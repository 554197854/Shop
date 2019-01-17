package com.shop.sso.service.impl;

import com.shop.common.ShopMsgResult;
import com.shop.mapper.TbUserMapper;
import com.shop.pojo.TbUser;
import com.shop.pojo.TbUserExample;
import com.shop.sso.jedis.JedisClient;
import com.shop.sso.service.UserLoginService;
import com.shop.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;

/**
 * @author N
 * @create 2019/1/16 -- 17:47
 * @email 554197854@qq.com
 */
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private TbUserMapper mapper;
    @Autowired
    private JedisClient jedisClient;

    @Override
    public ShopMsgResult login(String username, String password) {
        if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
            return ShopMsgResult.build(400,"用户名或者密码错误");
        }
        TbUserExample tbUserExample = new TbUserExample();
        tbUserExample.createCriteria().andUsernameEqualTo(username);
        List<TbUser> tbUsers = mapper.selectByExample(tbUserExample);
        if(tbUsers!=null&&tbUsers.size()>0){
            TbUser tbUser = tbUsers.get(0);
            String tbUserPassword = DigestUtils.md5DigestAsHex(password.getBytes());
            if(tbUserPassword.equals(tbUser.getPassword())){
                tbUser.setPassword("");
                String token = UUID.randomUUID().toString();
                jedisClient.set("SESSION:"+token, JsonUtils.objectToJson(tbUser));
                jedisClient.expire("SESSION:"+token, 1800);
                return ShopMsgResult.ok(token);
            }

        }
            return ShopMsgResult.build(400,"用户名或密码错误");

    }

    @Override
    public ShopMsgResult getUserByToken(String token) {
        String user = jedisClient.get("SESSION:" + token);
        if (StringUtils.isNotBlank(user)){
            jedisClient.expire("SESSION:"+token,1800);
            return ShopMsgResult.ok(JsonUtils.jsonToPojo(user,TbUser.class));
        }
        return ShopMsgResult.build(400,"用户已过期");
    }

    @Override
    public ShopMsgResult loginOut(String token) {
        String user = jedisClient.get("SESSION:" + token);
        if (StringUtils.isNotBlank(user)){
            jedisClient.ttl("SESSION:" + token);
            return ShopMsgResult.ok();
        }
        return ShopMsgResult.build(400,"无效用户");
    }
}
