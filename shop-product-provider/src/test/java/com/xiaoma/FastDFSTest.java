package com.xiaoma;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;
import java.util.Arrays;

@SpringBootTest
public class FastDFSTest {

    /**
     * 测试上传文件
     * @throws Exception
     */
    @Test
    public void testUpload() throws Exception{
        // 1.加载配置文件
        ClientGlobal.initByProperties("fastdfs_client.properties");

        // 2.创建TrackerClient
        TrackerClient trackerClient = new TrackerClient();

        // 3.通过TrackerClient获取TrackerServer
        TrackerServer trackerServer = trackerClient.getTrackerServer();

        // 4.获取StorageClient,通过StorageClient上传文件
        StorageClient storageClient = new StorageClient(trackerServer, null);

        // 5.上传图片
        String[] paths = storageClient.upload_file("E:\\2023_2_27\\课程19_带你从零玩转企业级分布式电商系统\\01_企业级分布式电商系统\\01_企业级分布式电商系统\\resources\\04\\测试图片\\2.jpg",
                "jpg", new NameValuePair[]{new NameValuePair("filename", "2.jpg")});
        Arrays.stream(paths).forEach(System.out::println);
    }


    /**
     * 测试下载文件
     * @throws Exception
     */
    @Test
    public void testDownload() throws Exception{

        // 1.加载配置文件
        ClientGlobal.initByProperties("fastdfs_client.properties");

        // 2.创建TrackerClient
        TrackerClient trackerClient = new TrackerClient();

        // 3.通过TrackerClient获取TrackerServer
        TrackerServer trackerServer = trackerClient.getTrackerServer();

        // 4.获取StorageClient,通过StorageClient上传文件
        StorageClient storageClient = new StorageClient(trackerServer, null);

        // 5.下载文件
        byte[] fileBytes = storageClient.download_file("group1",
                "M00/00/00/CgAICmTc1oaAe5brAATRZmOT604632.jpg");

        // 6.通过IO流将字节写入本地文件中
        FileOutputStream fos = new FileOutputStream("D:\\CodeRepository\\img\\shop\\download1.jpg");
        fos.write(fileBytes);
        fos.close();

    }

    /**
     * 测试删除文件
     * @throws Exception
     */
    @Test
    public void testDelete() throws Exception{

        // 1.加载配置文件
        ClientGlobal.initByProperties("fastdfs_client.properties");

        // 2.创建TrackerClient
        TrackerClient trackerClient = new TrackerClient();

        // 3.通过TrackerClient获取TrackerServer
        TrackerServer trackerServer = trackerClient.getTrackerServer();

        // 4.获取StorageClient,通过StorageClient上传文件
        StorageClient storageClient = new StorageClient(trackerServer, null);

        // 5.删除文件
        storageClient.delete_file("group1",
                "M00/00/00/CgAICmTc1oaAe5brAATRZmOT604632.jpg");

    }

}
