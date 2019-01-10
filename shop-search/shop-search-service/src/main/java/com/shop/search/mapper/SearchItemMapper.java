package com.shop.search.mapper;

import com.shop.common.SearchItem;

import java.util.List;

/**
 * @author N
 * @create 2019/1/2 -- 17:18
 * @email 554197854@qq.com
 */
public interface SearchItemMapper {
    public List<SearchItem> getSearchItemList();
    public SearchItem getSearchItemById(Long item_id);
}
