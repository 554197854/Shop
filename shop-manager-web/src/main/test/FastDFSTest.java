
import com.shop.utils.FastDFSUtils;
import org.csource.fastdfs.*;
import org.junit.Test;

/**
 * @author N
 * @create 2018/12/25 -- 19:59
 * @email 554197854@qq.com
 */
public class FastDFSTest {
    @org.junit.Test
    public void testUpload() throws Exception {
        FastDFSUtils fastDFSUtils = FastDFSUtils.getFastDFS();
        String url = fastDFSUtils.uploadFile("D:\\default_icon.png", "png", null);
        System.out.println(url);
    }
}
