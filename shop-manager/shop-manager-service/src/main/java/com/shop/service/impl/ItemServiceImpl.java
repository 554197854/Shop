package com.shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shop.common.EUDataGridResult;
import com.shop.common.ShopMsgResult;
import com.shop.mapper.TbItemDescMapper;
import com.shop.mapper.TbItemMapper;
import com.shop.mapper.TbItemParamItemMapper;
import com.shop.pojo.TbItem;
import com.shop.pojo.TbItemDesc;
import com.shop.pojo.TbItemExample;
import com.shop.pojo.TbItemParamItem;
import com.shop.service.ItemService;
import com.shop.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author N
 * @create 2018/12/21 -- 20:11
 * @email 554197854@qq.com
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    @Autowired
    private TbItemMapper tbItemMapper;
    @Override
    public TbItem getItemById(long id) {
        return tbItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public EUDataGridResult getItemList(int page, int rows) {
        TbItemExample example = new TbItemExample();
        example.createCriteria().andIdIsNotNull();
        PageHelper.startPage(page,rows);
        List<TbItem> tbItems = tbItemMapper.selectByExample(example);
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        PageInfo pageInfo = new PageInfo(tbItems);

        euDataGridResult.setTotal(pageInfo.getTotal());
        euDataGridResult.setRows(tbItems);


        return euDataGridResult;
    }

    @Override
    public ShopMsgResult insertItem(TbItem tbItem, String desc, String paramData) {
        long id = IDUtils.genItemId();
        tbItem.setId(id);
        tbItem.setStatus((byte)1);
        Date date = new Date();
        tbItem.setCreated(date);
        tbItem.setUpdated(date);

        tbItemMapper.insert(tbItem);
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(id);
        itemDesc.setCreated(date);
        itemDesc.setUpdated(date);
        itemDesc.setItemDesc(desc);
        tbItemDescMapper.insert(itemDesc);

        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setItemId(id);
        tbItemParamItem.setParamData(paramData);
        tbItemParamItem.setCreated(date);
        tbItemParamItem.setUpdated(date);
        tbItemParamItemMapper.insert(tbItemParamItem);

        return ShopMsgResult.ok();
    }
}
