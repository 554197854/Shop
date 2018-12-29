package com.shop.service.impl;

import com.shop.common.EUTreeNode;
import com.shop.mapper.TbItemCatMapper;
import com.shop.pojo.TbItemCat;
import com.shop.pojo.TbItemCatExample;
import com.shop.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author N
 * @create 2018/12/22 -- 23:16
 * @email 554197854@qq.com
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    TbItemCatMapper tbItemCatMapper;
    @Override
    public List<EUTreeNode> getItemCatList(long parentId) {
        TbItemCatExample example = new TbItemCatExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        List<TbItemCat> tbItemCats = tbItemCatMapper.selectByExample(example);
        List<EUTreeNode> list =  new ArrayList<>();
        for(TbItemCat tbItemCat:tbItemCats){
            EUTreeNode euTreeNode = new EUTreeNode();
            euTreeNode.setId(tbItemCat.getId());
            euTreeNode.setText(tbItemCat.getName());
            euTreeNode.setState(tbItemCat.getIsParent()?"closed":"open");
            list.add(euTreeNode);
        }

        return list;
    }
}
