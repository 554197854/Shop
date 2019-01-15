package com.shop.item.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author N
 * @create 2019/1/14 -- 19:29
 * @email 554197854@qq.com
 */
@Controller
public class HtmlController {


    @Autowired
    private FreeMarkerConfigurer config;

    @RequestMapping("/Html")
    @ResponseBody
    public String getHtml() throws Exception{
        System.out.println("go");
        //1.根据config 获取configuration对象
        Configuration configuration = config.getConfiguration();
        //2.设置模版文件加载文件夹
        Template template = configuration.getTemplate("template.ftl");
        //3.创建数据集，从数据库获取
        Map model = new HashMap();
        model.put("springtest","hellow");
        //4.创建writer
        Writer writer = new FileWriter(new File("D:/hellow.html"));
        //5.调用方法输出
       template.process(model,writer);
        //6.关闭
        writer.close();

        return "ok";
    }
}
