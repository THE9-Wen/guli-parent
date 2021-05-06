package com.wenhao.serviceedu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wenhao.commonutils.R;
import com.wenhao.serviceedu.entity.Course;
import com.wenhao.serviceedu.entity.Teacher;
import com.wenhao.serviceedu.service.CourseService;
import com.wenhao.serviceedu.service.TeacherService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Wenhao Tong
 * @Description
 * @create 2021-05-06 23:11
 */
@Api("普通用户获取教师信息")
@RestController
@RequestMapping("/edu/index")
@CrossOrigin
public class IndexController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    //查询前8条热门课程，查询前4条名师
    @GetMapping
    public R index(){
        // 查询前4的名师
        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<Teacher>();
        teacherQueryWrapper.orderByDesc("id");
        teacherQueryWrapper.last("limit 4");
        List<Teacher> teachers = teacherService.list(teacherQueryWrapper);

        //查询前8的课程
        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.orderByDesc("id");
        courseQueryWrapper.last("limit 8");
        List<Course> courses = courseService.list(courseQueryWrapper);

        //返回数据
        return R.ok().data("teachers",teachers).data("courses",courses);
    }



}
