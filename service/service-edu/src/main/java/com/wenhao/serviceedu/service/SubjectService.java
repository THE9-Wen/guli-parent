package com.wenhao.serviceedu.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Wenhao Tong
 * @Description
 * @create 2021-05-02 22:48
 */
public interface SubjectService {
    void batchImport(MultipartFile file);
}
