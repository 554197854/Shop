package com.shop.search.service.impl;

import com.shop.common.SearchItem;
import com.shop.common.SearchResult;
import com.shop.common.ShopMsgResult;
import com.shop.search.dao.SearchDao;
import com.shop.search.mapper.SearchItemMapper;
import com.shop.search.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
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
    private SearchDao searchDao;

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

    @Override
    public SearchResult search(String queryString, Integer page, Integer rows) throws Exception {
        //1.创建solrquery对象没
        SolrQuery solrQuery = new SolrQuery();
        //2.设置查询条件
        if(StringUtils.isNotBlank(queryString)){
            solrQuery.setQuery(queryString);
        }else{
            solrQuery.setQuery("*:*");
        }
        solrQuery.setStart((page-1)*rows);
        solrQuery.setRows(rows);
        //设置查询搜索域
        solrQuery.set("df","item_keywords");
        //设置高亮显示
        solrQuery.setHighlight(true);
        solrQuery.setHighlightSimplePre("<em style=\"color:red\">");//设置前缀
        solrQuery.setHighlightSimplePost("</em>");//设置后缀
        solrQuery.addHighlightField("item_title");//设置高亮显示域

        SearchResult result = searchDao.search(solrQuery);
        //设置总页数
        result.setPageCount((result.getRecordCount()%rows)>0?result.getRecordCount()/rows+1:result.getRecordCount()/rows);//没除净 总页数+1

        return result;
    }

    @Override
    public ShopMsgResult updateSearchItem(Long item_id) throws Exception {

        return searchDao.updateSearchItem(item_id);
    }
}
