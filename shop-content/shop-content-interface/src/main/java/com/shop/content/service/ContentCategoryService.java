package com.shop.content.service;

import com.shop.common.EUTreeNode;
import com.shop.common.ShopMsgResult;

import java.util.List;

/**
 * @author N
 * @create 2018/12/30 -- 21:24
 * @email 554197854@qq.com
 */

public interface ContentCategoryService {
    List<EUTreeNode> getContentCategoryList(long id);

    ShopMsgResult createContentCategory(Long parentId,String name);
}
