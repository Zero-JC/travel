package com.zero.travel.pojo.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @author LJC
 * @version 1.0
 * @date 2021/3/18 15:45
 */
@Data
public class UploadDTO implements Serializable {

    /**
     * 根目录 仅存储 不使用
     */
    private String rootPath;

    /**
     * 上传文件名
     */
    private String fileName;

    /**
     * 文件对象
     */
    private MultipartFile file;

}
