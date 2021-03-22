package com.zero.travel.service.common;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.zero.travel.pojo.dto.UploadDTO;
import com.zero.travel.pojo.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * OSS文件上传
 * @author LJC
 * @version 1.0
 * @date 2021/3/22 11:50
 */
@Service
public class OssService {

    @Value("${endpoint}")
    private String endpoint;

    @Value("${accessKeyId}")
    private String accessKeyId;

    @Value("${accessKeySecret}")
    private String accessKeySecret;


    /**
     * 测试 - 上传
     * @throws Exception
     */
    public void uploadTest() throws Exception {

        final OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        InputStream inputStream = new FileInputStream("F:\\temp001.png");

        // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
        ossClient.putObject("travel-route", "temp/temp001.png", inputStream);
        ossClient.shutdown();
    }

    /**
     * 测试 - 删除
     * @throws Exception
     */
    public void delete() throws Exception{
        final OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        ossClient.deleteObject("travel-route", "temp/temp001.png");
        ossClient.shutdown();
    }

    /**
     * 图片上传
     * @param uploadDTO
     * @throws Exception
     */
    public void imageUpload(UploadDTO uploadDTO) throws Exception {

        final OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        InputStream inputStream = uploadDTO.getFile().getInputStream();
        String imagePath = "travel/image/route/"+uploadDTO.getFileName();
        // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
        ossClient.putObject("travel-route", imagePath, inputStream);
        ossClient.shutdown();

    }

    /**
     * 删除图片
     * @throws Exception
     */
    public void deleteOssImage(String imagePath) throws Exception{
        final OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.deleteObject("travel-route", imagePath);
        ossClient.shutdown();
    }

}
