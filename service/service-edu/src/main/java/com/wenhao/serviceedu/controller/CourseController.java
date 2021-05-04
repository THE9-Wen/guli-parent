package com.wenhao.serviceedu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wenhao.commonutils.R;
import com.wenhao.serviceedu.entity.Course;
import com.wenhao.serviceedu.entity.CourseForm;
import com.wenhao.serviceedu.entity.query.CourseQuery;
import com.wenhao.serviceedu.entity.vo.CoursePublishVo;
import com.wenhao.serviceedu.service.CourseService;
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
@Api("课程管理")
@CrossOrigin
@RestController
@RequestMapping("/serviceedu/course")
public class CourseController {

    @Autowired
    private CourseService courseService;


    @ApiOperation(value = "新增课程")
    @PostMapping
    public R saveCourseInfo(
            @ApiParam(name = "CourseInfoForm", value = "课程基本信息", required = true)
            @RequestBody CourseForm courseForm) {
        String courseId = courseService.saveCourseInfo(courseForm);
        return R.ok().data("courseId",courseId);
    }

    @ApiOperation(value = "根据id查课程信息")
    @GetMapping("{id}")
    public R getById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable("id") String id) {
        CourseForm courseForm = courseService.getCourseFormById(id);
        return R.ok().data("item", courseForm);
    }

    @ApiOperation(value = "根据id查课程发布信息")
    @GetMapping("course-publish-info/{id}")
    public R getPublishInfoById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable("id") String id) {
        CoursePublishVo publishInfo = courseService.getCoursePublishVoById(id);
        return R.ok().data("item", publishInfo);
    }

    @ApiOperation(value = "课程的逻辑删除功能")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable("id") String id) {
        boolean res = courseService.removeById(id);
        if (res) {
            return R.ok();
        } else {
            return R.error().message("删除失败");
        }
    }

    @ApiOperation(value = "根据id修改")
    @PutMapping("{id}")
    public R updateById(@ApiParam(name = "id", value = "id", required = true)
                        @PathVariable String id,
                        @ApiParam(name = "course", value = "课程对象", required = true)
                        @RequestBody CourseForm courseForm) {
        courseForm.setId(id);
        boolean b = courseService.updateCourseInfo(courseForm);
        return R.ok();
    }

    @ApiOperation(value = "根据条件查课程信息")
    @GetMapping("{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable("page") Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable("limit") Long limit,
            @ApiParam(name = "courseQuery", value = "查询对象", required = true)
                    CourseQuery courseQuery) {
        Page<Course> pageParam = new Page<>(page, limit);
        courseService.pageQuery(pageParam, courseQuery);
        List<Course> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "根据id发布课程")
    @PutMapping("publishCourse/{id}")
    public R publishCourseById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id) {
        boolean b = courseService.publishCourseById(id);
        return R.ok();
    }


}

