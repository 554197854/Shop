package com.shop.content.service.impl;

import com.shop.common.ShopMsgResult;
import com.shop.content.service.ContentCategoryService;
import com.shop.common.EUTreeNode;
import com.shop.mapper.TbContentCategoryMapper;
import com.shop.pojo.TbContentCategory;
import com.shop.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author N
 * @create 2018/12/30 -- 21:25
 * @email 554197854@qq.com
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public List<EUTreeNode> getContentCategoryList(long id) {

        TbContentCategoryExample example = new TbContentCategoryExample();
        example.createCriteria().andParentIdEqualTo(id);
        List<TbContentCategory> tbContentCategories = tbContentCategoryMapper.selectByExample(example);
        List<EUTreeNode> treeNodeList = new ArrayList<>();
        for(TbContentCategory tbContentCategory:tbContentCategories){
            EUTreeNode treeNode = new EUTreeNode();
            treeNode.setId(tbContentCategory.getId());
            treeNode.setState(tbContentCategory.getIsParent()?"closed":"open");
            treeNode.setText(tbContentCategory.getName());
            treeNodeList.add(treeNode);
        }
        return treeNodeList;
    }


    @Override
    public ShopMsgResult createContentCategory(Long parentId, String name) {
        TbContentCategory tbContentCategory =new TbContentCategory();
        tbContentCategory.setParentId(parentId);
        tbContentCategory.setName(name);
        tbContentCategory.setSortOrder(1);
        tbContentCategory.setStatus(1);
        tbContentCategory.setIsParent(false);
        Date date = new Date();
        tbContentCategory.setCreated(date);
        tbContentCategory.setUpdated(date);
        tbContentCategoryMapper.insert(tbContentCategory);

        TbContentCategory tbContentCategoryParent = tbContentCategoryMapper.selectByPrimaryKey(parentId);
        if(!tbContentCategoryParent.getIsParent()){
            tbContentCategoryParent.setIsParent(true);
            tbContentCategoryParent.setUpdated(date);
            tbContentCategoryMapper.updateByPrimaryKey(tbContentCategoryParent);
        }

        return ShopMsgResult.ok(tbContentCategory);
    }
}
