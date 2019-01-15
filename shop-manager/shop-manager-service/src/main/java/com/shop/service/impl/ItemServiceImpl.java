package com.shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shop.common.EUDataGridResult;
import com.shop.common.ShopMsgResult;
import com.shop.mapper.TbItemDescMapper;
import com.shop.mapper.TbItemMapper;
import com.shop.mapper.TbItemParamItemMapper;
import com.shop.pojo.*;
import com.shop.service.ItemService;
import com.shop.service.jedis.JedisClient;
import com.shop.utils.IDUtils;
import com.shop.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.Date;
import java.util.List;

/**
 * @author N
 * @create 2018/12/21 -- 20:11
 * @email 554197854@qq.com
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private JedisClient jedisClient;

    @Resource(name = "topicDestination")
    private Destination destination;

    @Override
    public TbItem getItemById(Long id) {
        TbItem tbItem = tbItemMapper.selectByPrimaryKey(id);

        try {
            if(!jedisClient.exists("ITEM_INFO:"+id+":BASE")){
                jedisClient.set("ITEM_INFO:"+id+":BASE", JsonUtils.objectToJson(tbItem));
                jedisClient.expire("ITEM_INFO:"+id+":BASE",60*60*24);
            }else{
                String s = jedisClient.get("ITEM_INFO:" + id + ":BASE");
                if (StringUtils.isNotBlank(s)){
                    return JsonUtils.jsonToPojo(s,TbItem.class);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }


        return tbItem;
    }

    @Override
    public EUDataGridResult getItemList(int page, int rows) {
        TbItemExample example = new TbItemExample();
        example.createCriteria().andIdIsNotNull();
        PageHelper.startPage(page,rows);
        List<TbItem> tbItems = tbItemMapper.selectByExample(example);
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        PageInfo pageInfo = new PageInfo(tbItems);

        euDataGridResult.setTotal(pageInfo.getTotal());
        euDataGridResult.setRows(tbItems);


        return euDataGridResult;
    }

    @Override
    public ShopMsgResult insertItem(TbItem tbItem, String desc, String paramData) {
        final long id = IDUtils.genItemId();
        tbItem.setId(id);
        tbItem.setStatus((byte)1);
        Date date = new Date();
        tbItem.setCreated(date);
        tbItem.setUpdated(date);

        tbItemMapper.insert(tbItem);
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(id);
        itemDesc.setCreated(date);
        itemDesc.setUpdated(date);
        itemDesc.setItemDesc(desc);
        tbItemDescMapper.insert(itemDesc);

        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setItemId(id);
        tbItemParamItem.setParamData(paramData);
        tbItemParamItem.setCreated(date);
        tbItemParamItem.setUpdated(date);
        tbItemParamItemMapper.insert(tbItemParamItem);
        solrupdate(id);


        return ShopMsgResult.ok();
    }


    public void solrupdate(final long id){
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                System.out.println("create:id"+id);
                return session.createTextMessage(id+"");

            }
        });
    }

    @Override
    public TbItemDesc getItemDescById(Long item_id) {
        TbItemDesc itemDesc = tbItemDescMapper.selectByPrimaryKey(item_id);
        return itemDesc;
    }

    @Override
    public TbItemParamItem geItemParamItemByItemId(Long item_id) {
        TbItemParamItemExample example = new TbItemParamItemExample();
        example.createCriteria().andItemIdEqualTo(item_id);
        List<TbItemParamItem> itemParamItems = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
        if(itemParamItems.size()>0&&itemParamItems!=null){
            return itemParamItems.get(0);
        }
        return null;
    }
}
