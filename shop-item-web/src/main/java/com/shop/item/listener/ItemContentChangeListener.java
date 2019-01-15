package com.shop.item.listener;

import com.shop.pojo.TbItem;
import com.shop.pojo.TbItemDesc;
import com.shop.service.ItemService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author N
 * @create 2019/1/14 -- 17:13
 * @email 554197854@qq.com
 */
public class ItemContentChangeListener implements MessageListener {

    @Autowired
    private FreeMarkerConfigurer config;

    @Autowired
    private ItemService itemService;
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {

            try {
                Long item_id = Long.parseLong(((TextMessage) message).getText());
                TbItem tbItem = itemService.getItemById(item_id);
                TbItemDesc itemDesc = itemService.getItemDescById(item_id);
                genHtml(tbItem,itemDesc);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void genHtml(TbItem tbItem,TbItemDesc itemDesc) throws Exception{
        //1.根据config 获取configuration对象
        Configuration configuration = config.getConfiguration();
        //2.设置模版文件加载文件夹
        Template template = configuration.getTemplate("item.ftl");
        //3.创建数据集，从数据库获取
        Map model = new HashMap();
        model.put("tbItem",tbItem);
        model.put("itemDesc",itemDesc);
        //4.创建writer
        Writer writer = new FileWriter(new File("D:/hellow.html"));
        //5.调用方法输出
        template.process(model,writer);
        //6.关闭
        writer.close();
    }
}
