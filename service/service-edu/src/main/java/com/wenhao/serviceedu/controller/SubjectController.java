package com.wenhao.serviceedu.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.wenhao.commonutils.R;
import com.wenhao.serviceedu.entity.ExcelSubject;
import com.wenhao.serviceedu.entity.Subject;
import com.wenhao.serviceedu.entity.vo.ExcelSubjectListener;
import com.wenhao.serviceedu.entity.vo.SubjectNestedVo;
import com.wenhao.serviceedu.entity.vo.SubjectVo;
import com.wenhao.serviceedu.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Wenhao Tong
 * @Description
 * @create 2021-05-02 22:47
 */
@Api("课程分类管理")
@RestController
@RequestMapping("/serviceedu/subject")
@CrossOrigin
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @ApiOperation("添加课程")
    @PostMapping("addSubject")
    public R uploadCourses(@RequestBody MultipartFile file){
        subjectService.batchImport(file);
        return R.ok();
    }

    @GetMapping("getAllSubject")
    public R getAllSubject(){
        List<Subject> list = subjectService.list(null);
        return R.ok().data("list",list);
    }

    @GetMapping
    public R getNestedList(){
        List<SubjectNestedVo> list = subjectService.nestedList();
        return R.ok().data("list",list);
    }


}
