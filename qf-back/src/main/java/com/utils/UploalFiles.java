package com.utils;

import com.config.SystemConfig;
import io.minio.MinioClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * minio文件上传
 *
 * @Auth chengst
 * @Date Created in  18:04 2020/6/1
 */
public class UploalFiles {


    /**
     * 描述: 文件上传
     * params1  文件流
     * params2  bucketName
     * params3  文件夹名称
     *
     * @Author: chengst
     * @Date: 20:58 2020/6/1
     */
    public static String upload(MultipartFile file, String bucketName, String newFolder) throws Exception {
        if (file.isEmpty() || file.getSize() == 0) {
            return "文件为空";
        }
        MinioClient minioClient = new MinioClient(SystemConfig.MINIO_URL, SystemConfig.MINIO_ACCESSKEY, SystemConfig.MINIO_SERCETKEY);    //连接
        //   如果不存在名为“homepage”的bucket就创建一个
        if (!minioClient.bucketExists(bucketName)) {
            minioClient.makeBucket(bucketName);
        }
        //  获取原文件的名字
        String fileName = file.getOriginalFilename();
        //  加上文件夹后，新文件的名字
        //  pic会是bucket下的文件夹
        //  设置新文件夹时需要打开读写权限策略
        String newName = newFolder + "/" + UUID.randomUUID().toString().replaceAll("-", "")
                + fileName.substring(fileName.lastIndexOf("."));

        //获取file的inputStream
        InputStream inputStream = file.getInputStream();
        //  写入文件
        minioClient.putObject(bucketName, newName, inputStream, "application/octet-stream");
        inputStream.close();
        // 得到文件路径
        String url = minioClient.getObjectUrl(bucketName, newName);
        System.out.println(url);
        return url;
    }


    public static String getFiles(String buckName, String fileName) throws Exception {
        //连接
        MinioClient minioClient = new MinioClient(SystemConfig.MINIO_URL, SystemConfig.MINIO_ACCESSKEY, SystemConfig.MINIO_SERCETKEY);
        String s = minioClient.presignedGetObject(buckName, fileName);
        return s;
    }
}
