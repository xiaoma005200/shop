package com.xiaoma.util;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * FastDFS文件操作工具类
 */
@Component
public class FastDFSUtils {

    @Autowired
    StorageClient storageClient;

    /**
     * 文件上传
     * @param multipartFile 封装上传文件的信息
     * @return 如果上传成功返回文件路径,失败返回null
     */
    public String uploadFile(MultipartFile multipartFile, NameValuePair[] nameValuePairs) {
        try {
            // 1.获取原始文件名称===>abc.jpg
            String originalFilename = multipartFile.getOriginalFilename();

            // 2.获取文件后缀名
            String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

            // 3.利用FastDFS的api上传文件
            String[] paths = storageClient.upload_file(multipartFile.getBytes(), suffix, nameValuePairs);

            // 4.上传成功返回文件路径
            return paths[0]+"/"+paths[1];

        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        // 5.上传失败返回null
        return null;
    }

    public String uploadFile(MultipartFile multipartFile){
        return uploadFile(multipartFile,null);
    }

    // 文件下载

    //文件删除

}
