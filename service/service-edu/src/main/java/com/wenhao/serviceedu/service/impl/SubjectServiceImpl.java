package com.wenhao.serviceedu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenhao.serviceedu.entity.ExcelSubject;
import com.wenhao.serviceedu.entity.Subject;
import com.wenhao.serviceedu.entity.excel.ExcelSubjectListener;
import com.wenhao.serviceedu.entity.vo.SubjectNestedVo;
import com.wenhao.serviceedu.entity.vo.SubjectVo;
import com.wenhao.serviceedu.mapper.SubjectMapper;
import com.wenhao.serviceedu.service.SubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wenhao Tong
 * @Description
 * @create 2021-05-02 22:48
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public void batchImport(MultipartFile file) {
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            // 这里默认读取第一个sheet
            EasyExcel.read(inputStream, ExcelSubject.class, new ExcelSubjectListener(subjectMapper)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<SubjectNestedVo> nestedList() {

        List<SubjectNestedVo> list = new ArrayList<>();

        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", "0");
        queryWrapper.orderByAsc("sort", "id");
        List<Subject> oneSubjects = subjectMapper.selectList(queryWrapper);

        QueryWrapper<Subject> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.ne("parent_id", "0");
        queryWrapper.orderByAsc("sort", "id");
        List<Subject> twoSubjects = subjectMapper.selectList(queryWrapper2);

        int count = oneSubjects.size();
        for (int i = 0; i < count; i++) {
            Subject subject = oneSubjects.get(i);

            SubjectNestedVo subjectNestedVo = new SubjectNestedVo();
            BeanUtils.copyProperties(subject, subjectNestedVo);
            list.add(subjectNestedVo);

            int count2 = twoSubjects.size();
            for (int j = 0; j < count2; j++) {
                Subject subSubject = twoSubjects.get(j);
                if (subject.getId().equals(subSubject.getParentId())){
                    SubjectVo subjectVo = new SubjectVo();
                    BeanUtils.copyProperties(subSubject, subjectVo);
                    list.get(i).getChildren().add(subjectVo);
                }
            }
        }
        return list;
    }
}
