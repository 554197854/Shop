package com.shop.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shop.common.EUDataGridResult;
import com.shop.common.ShopMsgResult;
import com.shop.content.service.ContentService;
import com.shop.mapper.TbContentMapper;
import com.shop.pojo.TbContent;
import com.shop.pojo.TbContentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public EUDataGridResult getContentList(Long categoryId,Integer page,Integer rows) {
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        PageHelper.startPage(page,rows);
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
        return ShopMsgResult.ok();
    }

    @Override
    public List<TbContent> getContentByCategoryId(Long categoryId) {
        TbContentExample tbContentExample = new TbContentExample();
        tbContentExample.createCriteria().andCategoryIdEqualTo(categoryId);
        return tbContentMapper.selectByExample(tbContentExample);
    }
}
