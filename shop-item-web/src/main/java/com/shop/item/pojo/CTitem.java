package com.shop.item.pojo;

import com.shop.pojo.TbItem;
import com.shop.pojo.TbItemDesc;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

/**
 * @author N
 * @create 2019/1/10 -- 18:05
 * @email 554197854@qq.com
 */
public class CTitem extends TbItem {

    public CTitem(TbItem tbItem){
        BeanUtils.copyProperties(tbItem,this);
    }

    public String[] getImages(){
        if(StringUtils.isNotBlank(super.getImage())){
            return super.getImage().split(",");
        }
        return null;
    }
}
