package com.shop.search.dao;

import com.shop.common.SearchItem;
import com.shop.common.SearchResult;
import com.shop.common.ShopMsgResult;
import com.shop.search.mapper.SearchItemMapper;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author N
 * @create 2019/1/3 -- 16:55
 * @email 554197854@qq.com
 */

@Repository
public class SearchDao {
    //1.创建solrserver对象
    @Autowired
    private SolrServer solrServer;

    @Autowired
    private SearchItemMapper mapper;

    public SearchResult search(SolrQuery query) throws Exception {

        //2.直接执行查询
        QueryResponse response = solrServer.query(query);
        //3.获取结果集
        SolrDocumentList solrDocuments = response.getResults();
        //创建自定义的searchResult对象
        SearchResult searchResult = new SearchResult();
        searchResult.setRecordCount(solrDocuments.getNumFound());//设置总记录数
        //设置高亮
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        //4.遍历结果
        List itemList = new ArrayList();
        for (SolrDocument solrDocument:solrDocuments){
            SearchItem item = new SearchItem();
            //对标题高亮处理
            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
            if(list!=null&&list.size()>0){
                item.setTitle(list.get(0));
            }else{
                item.setTitle(solrDocument.get("item_title").toString());
            }

            item.setId(Long.parseLong(solrDocument.get("id").toString()));

            item.setSell_point(solrDocument.get("item_sell_point").toString());
            item.setPrice(Long.parseLong(solrDocument.get("item_price").toString()));
            item.setImage(solrDocument.get("item_image").toString());
            item.setCategory_name(solrDocument.get("item_category_name").toString());
            //item.setItem_desc(solrDocument.get("item_desc").toString());
            itemList.add(item);
        }
        //5.设置属性
        searchResult.setItemList(itemList);
        return searchResult;
    }

    public ShopMsgResult updateSearchItem(Long item_id) throws Exception{
        SearchItem searchItem = mapper.getSearchItemById(item_id);
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
        solrServer.commit();
        System.out.println("commit:"+item_id);
        return  ShopMsgResult.ok();
    }

}
