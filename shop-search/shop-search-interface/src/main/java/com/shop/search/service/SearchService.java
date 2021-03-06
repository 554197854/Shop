package com.shop.search.service;

import com.shop.common.SearchItem;
import com.shop.common.SearchResult;
import com.shop.common.ShopMsgResult;

/**
 * @author N
 * @create 2019/1/2 -- 17:32
 * @email 554197854@qq.com
 */
public interface SearchService {
    public ShopMsgResult importAllItems()throws Exception;

    public SearchResult search(String queryString,Integer page,Integer rows) throws Exception;

    public ShopMsgResult updateSearchItem(Long item_id) throws Exception;
}
