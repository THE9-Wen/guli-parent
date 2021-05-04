package com.wenhao.serviceedu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wenhao.commonbase.exception.MyException;
import com.wenhao.commonutils.R;
import com.wenhao.serviceedu.entity.Teacher;
import com.wenhao.serviceedu.entity.query.TeacherQuery;
import com.wenhao.serviceedu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
        try {
            List<Teacher> list = teacherService.list(null);
            return R.ok().data("teachers", list);
        }catch (Exception e){
            throw new MyException(20001,"出现算数异常");
        }
    }

    //讲师的逻辑删除功能
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable("id") String id){
        boolean res = teacherService.removeById(id);
        if (res) {
            return R.ok();
        } else {
            return R.error().message("删除失败");
        }
    }

    @ApiOperation(value = "分页讲师列表,可以添加条件")
    @GetMapping("{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable("page") Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable("limit") Long limit,
            @ApiParam(name = "teacherQuery", value = "查询对象", required = true)
                    TeacherQuery teacherQuery){
        Page<Teacher> pageParam = new Page<>(page,limit);
        teacherService.pageQuery(pageParam, teacherQuery);
        List<Teacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping
    public R save(@ApiParam(name = "teacher", value = "教师对象", required = true)
                  @RequestBody Teacher teacher){
        teacherService.save(teacher);
        return R.ok();
    }

    @ApiOperation(value = "根据id查询")
    @GetMapping("{id}")
    public R getById(@ApiParam(name = "id", value = "id", required = true)
                     @PathVariable String id){
        Teacher teacher = teacherService.getById(id);
        return R.ok().data("teacher",teacher);
    }

    @ApiOperation(value = "根据id修改")
    @PutMapping("{id}")
    public R updateById(@ApiParam(name = "id", value = "id", required = true)
                        @PathVariable String id,
                        @ApiParam(name = "teacher", value = "教师对象", required = true)
                        @RequestBody Teacher teacher){
        teacher.setId(id);
        teacherService.updateById(teacher);
        return R.ok();
    }

}

