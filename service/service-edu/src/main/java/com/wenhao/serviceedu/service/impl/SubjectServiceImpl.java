package com.wenhao.serviceedu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.analysis.ExcelReadExecutor;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.wenhao.serviceedu.entity.ExcelSubject;
import com.wenhao.serviceedu.entity.vo.ExcelSubjectListener;
import com.wenhao.serviceedu.mapper.SubjectMapper;
import com.wenhao.serviceedu.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Wenhao Tong
 * @Description
 * @create 2021-05-02 22:48
 */
@Service
public class SubjectServiceImpl implements SubjectService {

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
}
