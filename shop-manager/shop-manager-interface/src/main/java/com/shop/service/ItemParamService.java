package com.shop.service;

import com.shop.common.EUDataGridResult;
import com.shop.common.ShopMsgResult;

/**
 * @author N
 * @create 2018/12/27 -- 1:35
 * @email 554197854@qq.com
 */

public interface ItemParamService {
    EUDataGridResult getItemParamList(int page, int rows);

    ShopMsgResult getItemParamByCatId(long catId);

    ShopMsgResult insertItemParam(long catId, String ParamData);
}
