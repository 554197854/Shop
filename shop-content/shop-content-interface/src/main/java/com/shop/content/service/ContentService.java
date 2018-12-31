package com.shop.content.service;

import com.shop.common.EUDataGridResult;
import com.shop.common.ShopMsgResult;
import com.shop.pojo.TbContent;

import java.util.List;

/**
 * @author N
 * @create 2018/12/31 -- 15:18
 * @email 554197854@qq.com
 */
public interface ContentService {
    EUDataGridResult getContentList(Long categoryId,Integer page,Integer rows);

    ShopMsgResult saveContent(TbContent tbContent);

    List<TbContent> getContentByCategoryId(Long categoryId);
}
