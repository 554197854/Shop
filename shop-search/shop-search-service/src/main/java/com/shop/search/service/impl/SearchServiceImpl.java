package com.shop.search.service.impl;

import com.shop.common.SearchItem;
import com.shop.common.ShopMsgResult;
import com.shop.search.mapper.SearchItemMapper;
import com.shop.search.service.SearchService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author N
 * @create 2019/1/2 -- 17:34
 * @email 554197854@qq.com
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchItemMapper mapper;

    @Autowired
    private SolrServer solrServer;

    @Override
    public ShopMsgResult importAllItems() throws Exception{
        List<SearchItem> searchItemList = mapper.getSearchItemList();
        for(SearchItem searchItem:searchItemList){
            SolrInputDocument document= new SolrInputDocument();
            document.addField("id",searchItem.getId()+"");
            document.addField("item_title",searchItem.getTitle());
            document.addField("item_sell_point",searchItem.getSell_point());
            document.addField("item_price",searchItem.getPrice());
            document.addField("item_image",searchItem.getImage());
            document.addField("item_category_name",searchItem.getCategory_name());
            document.addField("item_desc",searchItem.getItem_desc());
            //添加到索引库
            solrServer.add(document);
        }
        solrServer.commit();
        return ShopMsgResult.ok();
    }
}
