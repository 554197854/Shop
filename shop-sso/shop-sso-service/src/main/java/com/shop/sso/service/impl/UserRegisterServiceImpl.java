package com.shop.sso.service.impl;

import com.shop.common.ShopMsgResult;
import com.shop.mapper.TbUserMapper;
import com.shop.pojo.TbUser;
import com.shop.pojo.TbUserExample;
import com.shop.sso.service.UserRegisterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @author N
 * @create 2019/1/15 -- 21:45
 * @email 554197854@qq.com
 */
@Service
public class UserRegisterServiceImpl implements UserRegisterService {
    @Autowired
    TbUserMapper mapper;
    
    @Override
    public ShopMsgResult checkData(String param, Integer type) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        if(type==1){
            criteria.andUsernameEqualTo(param);
        }else if(type==2){
            criteria.andPhoneEqualTo(param);
        }else if(type==3){
            criteria.andEmailEqualTo(param);
        }else {
            return ShopMsgResult.build(400,"错误参数");
        }
        List<TbUser> tbUsers = mapper.selectByExample(example);
        if (tbUsers.size()>0&&tbUsers!=null){
            return ShopMsgResult.ok("false");
        }

        return ShopMsgResult.ok("true");
    }




    @Override
    public ShopMsgResult userRegister(TbUser user) {
        //1.注入mapper
        //2.校验数据
        //2.1 校验用户名和密码不能为空
        if(StringUtils.isEmpty(user.getUsername())){
            return ShopMsgResult.build(400, "注册失败. 请校验数据后请再提交数据");
        }
        if(StringUtils.isEmpty(user.getPassword())){
            return ShopMsgResult.build(400, "注册失败. 请校验数据后请再提交数据");
        }
        //2.2 校验用户名是否被注册了
        ShopMsgResult result = checkData(user.getUsername(),1);
        if(!(boolean)result.getData()){
            //数据不可用
            return ShopMsgResult.build(400, "注册失败. 请校验数据后请再提交数据");
        }
        //2.3 校验电话号码是否被注册了
        if(StringUtils.isNotBlank(user.getPhone())){
            ShopMsgResult result2 = checkData(user.getPhone(),2);
            if(!(boolean)result2.getData()){
                //数据不可用
                return ShopMsgResult.build(400, "注册失败. 请校验数据后请再提交数据");
            }
        }
        //2.4 校验email是否被注册了
        if(StringUtils.isNotBlank(user.getEmail())){
            ShopMsgResult result2 = checkData(user.getEmail(),3);
            if(!(boolean)result2.getData()){
                //数据不可用
                return ShopMsgResult.build(400, "注册失败. 请校验数据后请再提交数据");
            }
        }

        //3.如果校验成功   补全其他的属性
        user.setCreated(new Date());
        user.setUpdated(user.getCreated());
        //4.对密码进行MD5加密
        String md5password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5password);
        //5.插入数据
        mapper.insertSelective(user);
        //6.返回ShopMsgResult
        return ShopMsgResult.ok();
    }
}
