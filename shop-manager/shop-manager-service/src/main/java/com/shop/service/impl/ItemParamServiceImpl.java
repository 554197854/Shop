package com.shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shop.common.EUDataGridResult;
import com.shop.common.ShopMsgResult;
import com.shop.mapper.TbItemParamMapper;
import com.shop.pojo.TbItemParam;
import com.shop.pojo.TbItemParamExample;
import com.shop.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author N
 * @create 2018/12/27 -- 1:37
 * @email 554197854@qq.com
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {
    @Autowired
    TbItemParamMapper tbItemParamMapper;
    @Override
    public EUDataGridResult getItemParamList(int page, int rows) {
        PageHelper.startPage(page,rows);
        List itemParamList = tbItemParamMapper.getItemParamList();
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        PageInfo pageInfo = new PageInfo(itemParamList);
        euDataGridResult.setTotal(pageInfo.getTotal());
        euDataGridResult.setRows(itemParamList);
        return euDataGridResult;

    }

    @Override
    public ShopMsgResult insertItemParam(long catId, String ParamData) {
        TbItemParam param = new TbItemParam();
        param.setItemCatId(catId);
        param.setParamData(ParamData);
        Date date = new Date();
        param.setCreated(date);
        param.setUpdated(date);
        tbItemParamMapper.insert(param);
        return ShopMsgResult.ok();
    }

    @Override
    public ShopMsgResult getItemParamByCatId(long catId) {
        TbItemParamExample example = new TbItemParamExample();
        example.createCriteria().andItemCatIdEqualTo(catId);
        List<TbItemParam> tbItemParams = tbItemParamMapper.selectByExampleWithBLOBs(example);
        if(tbItemParams.size()!=0){
            return ShopMsgResult.ok(tbItemParams.get(0));
        }else{
            return ShopMsgResult.ok();
        }


    }
}
