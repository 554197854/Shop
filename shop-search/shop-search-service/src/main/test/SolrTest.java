import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;

/**
 * @author N
 * @create 2019/1/2 -- 17:36
 * @email 554197854@qq.com
 */
public class SolrTest {
    @Test
    public void add() throws IOException, SolrServerException {
        //1.创建solrserver服务
        SolrServer solrServer = new HttpSolrServer("http://192.168.31.99:8983/solr");
        //2.创建solrinputdocument
        SolrInputDocument document = new SolrInputDocument();
        //3.向文档添加域
        document.addField("id","test001");
        document.addField("item_title","测试");
        //4.储存到索引库
        solrServer.add(document);
        //5.提交
        solrServer.commit();
    }
}
