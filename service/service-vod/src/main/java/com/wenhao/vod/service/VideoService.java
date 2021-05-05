package com.wenhao.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Wenhao Tong
 * @Description
 * @create 2021-05-04 21:26
 */
public interface VideoService {
    String uploadVideo(MultipartFile file);

    void removeVideo(String id);

    void removeVideoList(List<String> videoIdList);
}
