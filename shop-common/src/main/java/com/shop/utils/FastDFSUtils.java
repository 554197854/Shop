package com.shop.utils;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.Serializable;

/**
 * @author N
 * @create 2018/12/26 -- 1:39
 * @email 554197854@qq.com
 */
public class FastDFSUtils implements Serializable {

        private TrackerClient trackerClient = null;
        private TrackerServer trackerServer = null;
        private StorageServer storageServer = null;
        //使用StorageClient1进行上传
        private StorageClient1 storageClient1 = null;

        private static FastDFSUtils fastDFSUtils = null;
        private FastDFSUtils(String conf) throws Exception {
            //获取classpath路径下配置文件"fdfs_client.conf"的路径
            //conf直接写相对于classpath的位置，不需要写classpath:
            String configPath = this.getClass().getClassLoader().getResource(conf).getFile();

            ClientGlobal.init(configPath);

            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            storageServer = trackerClient.getStoreStorage(trackerServer);
            storageClient1 = new StorageClient1(trackerServer, storageServer);
        }
        public static FastDFSUtils getFastDFS() throws Exception{
            if(fastDFSUtils==null){

                fastDFSUtils = new FastDFSUtils("resource/client.conf");

            }
            return fastDFSUtils;
        }

        public String uploadFile(byte[] file_buff, String file_ext_name,NameValuePair[] meta_list) throws Exception { //远程上传

            String result = storageClient1.upload_file1(file_buff, file_ext_name, meta_list);
            result="http://192.168.31.99:8080/"+result;//我的服务器地址+返回的图片存储路径
            return result;
        }

        public String uploadFile(String local_filename, String file_ext_name, NameValuePair[] meta_list) throws Exception { //本地上传

            String result = storageClient1.upload_file1(local_filename, file_ext_name, meta_list);
            result="http://192.168.31.99:8080/"+result; //我的服务器地址+返回的图片存储路径
            return result;
        }
}

