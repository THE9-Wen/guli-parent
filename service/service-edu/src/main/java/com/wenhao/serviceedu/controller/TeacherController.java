package com.wenhao.serviceedu.controller;

import com.wenhao.commonutils.R;
import com.wenhao.serviceedu.entity.Teacher;
import com.wenhao.serviceedu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author wenhao Tong
 * @since 2021-04-30
 */
@Api("讲师管理")
@RestController
@RequestMapping("/serviceedu/teacher")
@CrossOrigin
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    //查询所有的导师
    @ApiOperation(value = "列出所有老师")
    @GetMapping("/list")
    public R list(){
        List<Teacher> list = teacherService.list(null);
        return R;
    }

    //讲师的逻辑删除功能
    @DeleteMapping("{id}")
    public boolean removeById(@PathVariable("id") String id){
        return teacherService.removeById(id);
    }
}

