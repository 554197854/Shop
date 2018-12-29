package com.shop.service;

import com.shop.common.EUTreeNode;

import java.util.List;

/**
 * @author N
 * @create 2018/12/22 -- 23:15
 * @email 554197854@qq.com
 */
public interface ItemCatService {
    List<EUTreeNode> getItemCatList(long parentId);
}
