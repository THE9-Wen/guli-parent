package com.wenhao.serviceedu.controller;


import com.wenhao.commonutils.R;
import com.wenhao.serviceedu.entity.Chapter;
import com.wenhao.serviceedu.entity.vo.ChapterVo;
import com.wenhao.serviceedu.service.ChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author wenhao Tong
 * @since 2021-05-03
 */
@Api("课程章节管理")
@CrossOrigin
@RestController
@RequestMapping("/serviceedu/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;


    @ApiOperation("嵌套章节数据列表")
    @GetMapping("{id}")
    public R nestedListByCourseId(
            @ApiParam(name = "courseId",value = "课程ID",required = true)
            @PathVariable("id") String courseId){
        List<ChapterVo> chapterVos = chapterService.nestedList(courseId);
        return R.ok().data("items",chapterVos);
    }

    @ApiOperation("新增章节")
    @PostMapping
    public R addChapter(
            @ApiParam(name = "chapter",value = "章节信息",required = true)
            @RequestBody Chapter chapter){
        boolean save = chapterService.save(chapter);
        return R.ok();
    }

    @ApiOperation("删除章节")
    @DeleteMapping("{id}")
    public R removeChapter(
            @ApiParam(name = "chapterId",value = "章节ID",required = true)
            @PathVariable String id
    ){
        boolean result = chapterService.removeChapterById(id);
        if(result){
            return R.ok();
        }else{
            return R.error().message("删除失败");
        }
    }

    @ApiOperation("修改章节")
    @PutMapping("{id}")
    public R removeChapter(
            @ApiParam(name = "chapterId",value = "章节ID",required = true)
            @PathVariable String id,
            @ApiParam(name = "chapter",value = "章节信息",required = true)
            @RequestBody Chapter chapter
    ){
        chapter.setId(id);
        chapterService.updateById(chapter);
        return R.ok();
    }

    @ApiOperation("根据id查询章节")
    @GetMapping("getChapterInfo/{id}")
    public R getChapterById(
            @ApiParam(name = "chapterId",value = "章节ID",required = true)
            @PathVariable String id
    ){
        Chapter chapter = chapterService.getById(id);
        return R.ok().data("chapter",chapter);
    }


}

