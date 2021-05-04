package com.wenhao.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wenhao.serviceedu.entity.Teacher;
import com.wenhao.serviceedu.entity.query.TeacherQuery;
import com.wenhao.serviceedu.mapper.TeacherMapper;
import com.wenhao.serviceedu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author wenhao Tong
 * @since 2021-04-30
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Override
    public void pageQuery(Page<Teacher> pageParam, TeacherQuery teacherQuery) {
        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.orderByAsc("sort");

        if (teacherQuery == null){
            baseMapper.selectPage(pageParam, teacherQueryWrapper);
            return;
        }

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if (name != null) {
            teacherQueryWrapper.like("name",name);
        }

        if (level != null) {
            teacherQueryWrapper.eq("level",level);
        }

        if (begin != null) {
            teacherQueryWrapper.ge("begin",begin);
        }

        if (end != null) {
            teacherQueryWrapper.le("end",end);
        }

        baseMapper.selectPage(pageParam,teacherQueryWrapper);
    }

    @Override
    public Teacher getByName(String name) {
        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.eq("name",name);
        return baseMapper.selectOne(teacherQueryWrapper);
    }
}
