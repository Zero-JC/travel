package com.zero.travel.service.common;

import com.zero.travel.pojo.dto.UploadDTO;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * 上传服务-本地
 * @author LJC
 * @version 1.0
 * @date 2021/3/18 14:30
 */
@Service
public class UploadService {

    private static final Logger log = LoggerFactory.getLogger(UploadService.class);


    /**
     * 通用上传
     * @param uploadDTO
     */
    public void commonUpload(UploadDTO uploadDTO) throws Exception{
        final MultipartFile file = uploadDTO.getFile();
        if (ObjectUtils.isEmpty(file)){
            throw new Exception("文件不存在");
        }

        String uploadFilePath = uploadDTO.getRootPath()+ File.separator+uploadDTO.getFileName();
        log.info("文件物理路径:{}",uploadFilePath);
        File rootPath = new File(uploadDTO.getRootPath());
        //若目录不存在则创建
        if (!rootPath.exists()){
            rootPath.mkdirs();
        }
        //上传
        file.transferTo(new File(uploadFilePath));

    }
}
