package com.shop.controller;

import com.shop.common.ShopMsgResult;
import com.shop.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author N
 * @create 2019/1/2 -- 23:35
 * @email 554197854@qq.com
 */
@Controller
public class SolrController {


    @Autowired
    private SearchService searchService;

    @ResponseBody
    @RequestMapping(value = "/index/importAll",method = RequestMethod.POST)
    public ShopMsgResult importSolrIndex() throws Exception{
        return searchService.importAllItems();
    }
}
