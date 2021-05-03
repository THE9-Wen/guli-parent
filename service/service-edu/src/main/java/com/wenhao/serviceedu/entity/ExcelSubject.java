package com.wenhao.serviceedu.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author Wenhao Tong
 * @Description
 * @create 2021-05-02 22:52
 */
@Data
public class ExcelSubject {
    @ExcelProperty(index = 0)
    private String oneSubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
