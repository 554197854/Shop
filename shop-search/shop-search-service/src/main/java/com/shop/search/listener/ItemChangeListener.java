package com.shop.search.listener;

import com.shop.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author N
 * @create 2019/1/10 -- 2:38
 * @email 554197854@qq.com
 */
public class ItemChangeListener implements MessageListener {
    @Autowired
    private SearchService searchService;

    @Override
    public void onMessage(Message message) {

        if (message instanceof TextMessage) {
            try {
                String item_id = ((TextMessage) message).getText();
                searchService.updateSearchItem(Long.parseLong(item_id));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
