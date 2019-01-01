import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @author N
 * @create 2019/1/1 -- 19:35
 * @email 554197854@qq.com
 */
public class JedisTest {
    @Test
    public void TestConnectionCluster(){
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.31.99",7001));
        nodes.add(new HostAndPort("192.168.31.99",7002));
        nodes.add(new HostAndPort("192.168.31.99",7003));
        nodes.add(new HostAndPort("192.168.31.99",7004));
        nodes.add(new HostAndPort("192.168.31.99",7005));
        nodes.add(new HostAndPort("192.168.31.99",7006));
        JedisCluster jedisCluster=new JedisCluster(nodes);
    }

    @Test
    public void TestJedis(){
        Jedis jedis = new Jedis("192.168.31.99",6379);
        System.out.println(jedis.ping());
    }

}
