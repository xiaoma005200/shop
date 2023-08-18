package com.xiaoma.config;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * FastDFS实现文件上传配置类
 */
@Configuration
public class FastDFSConfig {

    @Bean
    public StorageClient storageClient() throws MyException, IOException {
        // 1.加载配置文件
        ClientGlobal.initByProperties("fastdfs_client.properties");

        // 2.创建TrackerClient
        TrackerClient trackerClient = new TrackerClient();

        // 3.通过TrackerClient获取TrackerServer
        TrackerServer trackerServer = trackerClient.getTrackerServer();

        // 4.获取StorageClient,通过StorageClient上传文件
        StorageClient storageClient = new StorageClient(trackerServer, null);

        return storageClient;
    }
}
