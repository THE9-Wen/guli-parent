package com.wenhao.serviceedu.entity.vo;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wenhao.commonbase.exception.MyException;
import com.wenhao.serviceedu.entity.ExcelSubject;
import com.wenhao.serviceedu.entity.Subject;
import com.wenhao.serviceedu.mapper.SubjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Wenhao Tong
 * @Description
 * @create 2021-05-02 23:05
 */
@Slf4j
public class ExcelSubjectListener extends AnalysisEventListener<ExcelSubject> {

    @Autowired
    private SubjectMapper subjectMapper;
    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param subjectMapper
     */
    public ExcelSubjectListener(SubjectMapper subjectMapper) {
        this.subjectMapper = subjectMapper;
    }

    /**
     * 每一行都会读取到某一门课的一级学科和二级学科
     * 需要判断这门课对应的一级和二级学科是否已经存在
     * 如果已经存在的话就不用再继续添加了，直接下一行
     *
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(ExcelSubject data, AnalysisContext context) {
        if (data == null) {
            throw new MyException(20001,"添加失败！");
        }

        //一级分类
        Subject subject1 = existOneSubject(data.getOneSubjectName());
        if (subject1 == null) {
            subject1 = new Subject();
            subject1.setTitle(data.getOneSubjectName());
            subject1.setParentId("0");
            subjectMapper.insert(subject1);
        }

        //获取一级分类的id
        String pid = subject1.getId();

        //二级分类
        Subject subject2 = existTwoSubject(data.getTwoSubjectName(),pid);
        if (subject2 == null) {
            subject2 = new Subject();
            subject2.setTitle(data.getTwoSubjectName());
            subject2.setParentId(pid);
            subjectMapper.insert(subject2);
        }
    }

    //判断一级分类是否重复
    private Subject existOneSubject(String name) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        Subject subject = subjectMapper.selectOne(wrapper);
        return subject;
    }

    //判断二级分类是否重复
    private Subject existTwoSubject(String name,String pid) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        Subject subject = subjectMapper.selectOne(wrapper);
        return subject;
    }

    //读取excel表头信息
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息：" + headMap);
    }

    //已经不用执行了
    //其实也可以在本地读取，存到一个List中，读完之后一次性提交到数据库中
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

}
