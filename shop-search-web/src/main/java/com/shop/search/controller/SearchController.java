package com.shop.search.controller;

import com.shop.common.SearchResult;
import com.shop.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author N
 * @create 2019/1/3 -- 19:20
 * @email 554197854@qq.com
 */
@Controller
public class SearchController {
    @Value("${SHOW_ROWS}")
    private Integer rows;
    @Autowired
    private SearchService searchService;

    @RequestMapping("/search")
    public  String search(@RequestParam(value = "page",defaultValue = "1")Integer page,@RequestParam(value = "q") String quryString,Model model)throws Exception{
        quryString = new String(quryString.getBytes("iso-8859-1"),"utf-8");

        SearchResult search = searchService.search(quryString, page, rows);
        model.addAttribute("query",quryString);
        model.addAttribute("itemList",search.getItemList());
        model.addAttribute("page",page);
        model.addAttribute("totalPages",search.getPageCount());
        return "search";
    }
}
