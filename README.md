# Shop 
####项目介绍
    之前做过的一个分销商城自己再重新搞一下，重温一下相关技术
####相关技术
    SSM
    
####遇到的一些问题
        一、使用IDEA install测试项目运行时 如果使用自己的maven仓库如D:/.m2 并且使用右侧Maven面板install但又用Maven goal命令行输入clean tomcat7:run启动项目时，会自动找默认C盘的.m2库，会产生
    无法找到依赖的错误，解决办法如下：1.所有install都采用Maven goal命令行模式，则都会在C盘默认目录进行编译打包。2.如果要用自己maven配置启动，则在IDEA菜单RUN Edit Configuration中
    编写maven clean tomcat7:run命令。而不再使用右侧面板的Maven goal命令
    
####项目基础结构层级关系
 
    shop-parent  （管理依赖jar包的版本，全局，公司级别版本包）
    |   |--shop-common （通用组件，时间，json等）
    |          
    |--shop-manager  （聚合工程）(商品后台系统，服务层)
        |
        |--shop-manager-dao
        |      
        |--shop-manager-interface
        |       
        |--shop-manager-pojo
        |       
        |--shop-manager-service
    |    
    |--shop-manager-web  (商品后台系统，表现层)
    |
    |--shop-content (聚合工程)  (门户展示系统，服务层)
         |
         |--shop-content-interface
         |   
         |--shop-content-service  
         |
    |--shop-portal-web (门户展示系统，表现层)    
    |
    |--shop-search (solr搜索服务系统，服务层)
         |
         |--shop-search-interface
         |
         |--shop-search-service
         |
    |--shop-search-web (solr搜索系统，表现层)
    |
    |--shop-item-web (商品详情展示系统，表现层)
    