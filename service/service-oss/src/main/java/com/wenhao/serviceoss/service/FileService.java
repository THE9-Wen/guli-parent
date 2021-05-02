package com.wenhao.serviceoss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Wenhao Tong
 * @Description
 * @create 2021-05-02 14:16
 */
public interface FileService {
    /**
     * 文件上传至阿里云
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
