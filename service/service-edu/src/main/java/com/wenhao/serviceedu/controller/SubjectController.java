package com.wenhao.serviceedu.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.wenhao.commonutils.R;
import com.wenhao.serviceedu.entity.ExcelSubject;
import com.wenhao.serviceedu.entity.vo.ExcelSubjectListener;
import com.wenhao.serviceedu.service.SubjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Wenhao Tong
 * @Description
 * @create 2021-05-02 22:47
 */
@Api("课程分类管理")
@RestController
@RequestMapping("/serviceedu/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping("course")
    public R uploadCourses(@RequestBody MultipartFile file){
        subjectService.batchImport(file);
        return R.ok();
    }
}
