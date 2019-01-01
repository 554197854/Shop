package com.shop.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shop.common.EUDataGridResult;
import com.shop.common.ShopMsgResult;
import com.shop.content.jedis.JedisClient;
import com.shop.content.service.ContentService;
import com.shop.mapper.TbContentMapper;
import com.shop.pojo.TbContent;
import com.shop.pojo.TbContentExample;
import com.shop.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.List;

/**
 * @author N
 * @create 2018/12/31 -- 15:19
 * @email 554197854@qq.com
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    TbContentMapper tbContentMapper;

    @Autowired
    private JedisClient jedisClient;
    @Value("${CONTENT_KEY}")
    private String CONTENT_KEY;

    @Override
    public EUDataGridResult getContentList(Long categoryId, Integer page, Integer rows) {
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        PageHelper.startPage(page, rows);
        TbContentExample tbContentExample = new TbContentExample();
        tbContentExample.createCriteria().andCategoryIdEqualTo(categoryId);
        List<TbContent> tbContentList = tbContentMapper.selectByExample(tbContentExample);
        PageInfo pageInfo = new PageInfo(tbContentList);
        euDataGridResult.setTotal(pageInfo.getTotal());
        euDataGridResult.setRows(tbContentList);

        return euDataGridResult;
    }

    @Override
    public ShopMsgResult saveContent(TbContent tbContent) {
        Date date = new Date();
        tbContent.setCreated(date);
        tbContent.setUpdated(date);
        tbContentMapper.insert(tbContent);
        try {
            jedisClient.hdel(CONTENT_KEY,tbContent.getCategoryId()+"");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ShopMsgResult.ok();
    }

    @Override
    public List<TbContent> getContentByCategoryId(Long categoryId) {

        try {
            String content = jedisClient.hget(CONTENT_KEY, categoryId + "");

            if (StringUtils.isNotBlank(content)) {
                System.out.println("有缓存");
                List tbContents = JsonUtils.jsonToList(jedisClient.hget(CONTENT_KEY, categoryId + ""), TbContent.class);
                return tbContents;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TbContentExample tbContentExample = new TbContentExample();
        tbContentExample.createCriteria().andCategoryIdEqualTo(categoryId);
        List<TbContent> tbContents = tbContentMapper.selectByExample(tbContentExample);

        try {
            System.out.println("没有缓存");
            jedisClient.hset(CONTENT_KEY, categoryId + "", JsonUtils.objectToJson(tbContents));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tbContents;

    }
}
