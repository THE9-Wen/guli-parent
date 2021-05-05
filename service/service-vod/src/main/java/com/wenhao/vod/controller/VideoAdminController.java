package com.wenhao.vod.controller;

import com.wenhao.commonutils.R;
import com.wenhao.vod.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Wenhao Tong
 * @Description
 * @create 2021-05-04 23:44
 */
@RestController
@CrossOrigin
@Api("阿里云视频点播微服务")
@RequestMapping("/admin/video")
public class VideoAdminController {

    @Autowired
    private VideoService videoService;

    @ApiOperation("上传视频")
    @PostMapping("/upload")
    public R uploadVideo(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestBody MultipartFile file){
        String videoId = videoService.uploadVideo(file);
        return R.ok().data("videoId",videoId);
    }

    @ApiOperation("删除视频")
    @DeleteMapping("/{id}")
    public R removeVideoById(
            @ApiParam(name = "id", value = "云端视频id", required = true)
            @PathVariable String id){
        videoService.removeVideo(id);
        return R.ok().message("视频删除成功！");
    }

    @ApiOperation("批量删除视频")
    @DeleteMapping("/delete-batch")
    public R removeVideoList(
            @ApiParam(name = "videoList", value = "需要删除的视频列表", required = true)
            @RequestBody List<String> videoList){
        videoService.removeVideoList(videoList);
        return R.ok().message("视频删除成功！");
    }
}
