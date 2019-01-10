package com.shop.service;

import com.shop.common.EUDataGridResult;
import com.shop.common.ShopMsgResult;
import com.shop.pojo.TbItem;

/**
 * @author N
 * @create 2018/12/21 -- 20:09
 * @email 554197854@qq.com
 */

public interface ItemService {

    TbItem getItemById(Long id);

    EUDataGridResult getItemList(int page, int rows);

    ShopMsgResult insertItem(TbItem tbItem, String desc, String paramData);



}
