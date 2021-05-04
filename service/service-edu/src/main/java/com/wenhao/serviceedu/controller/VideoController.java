package com.wenhao.serviceedu.controller;


import com.wenhao.commonutils.R;
import com.wenhao.serviceedu.entity.Chapter;
import com.wenhao.serviceedu.entity.Video;
import com.wenhao.serviceedu.service.VideoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author wenhao Tong
 * @since 2021-05-03
 */
@RestController
@CrossOrigin
@RequestMapping("/serviceedu/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @ApiOperation("新增小节")
    @PostMapping
    public R addChapter(
            @ApiParam(name = "video",value = "小节信息",required = true)
            @RequestBody Video video){
        boolean save = videoService.save(video);
        return R.ok();
    }

    @ApiOperation("编辑小节")
    @PutMapping("{id}")
    public R addChapter(
            @ApiParam(name = "video",value = "小节信息",required = true)
            @RequestBody Video video,
            @ApiParam(name = "videoId",value = "小节ID",required = true)
            @PathVariable String id){
        video.setId(id);
        boolean update = videoService.updateById(video);
        return R.ok();
    }

    @ApiOperation("删除小节")
    @DeleteMapping("{id}")
    public R removeChapter(
            @ApiParam(name = "videoId",value = "小节ID",required = true)
            @PathVariable String id){
        videoService.removeById(id);
        return R.ok();
    }

    @ApiOperation("根据ID查询小节")
    @GetMapping("{id}")
    public R getChapterById(
            @ApiParam(name = "videoId",value = "小节ID",required = true)
            @PathVariable String id){
        Video video = videoService.getById(id);
        return R.ok().data("video",video);
    }

}

