package com.shop.service.impl;

import com.shop.mapper.TestMapper;
import com.shop.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author N
 * @create 2018/12/29 -- 13:35
 * @email 554197854@qq.com
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    TestMapper testMapper;
    @Override
    public String getTime() {
        return testMapper.getTime();
    }
}

