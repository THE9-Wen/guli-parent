package com.wenhao.serviceedu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wenhao.serviceedu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wenhao.serviceedu.entity.vo.TeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author wenhao Tong
 * @since 2021-04-30
 */
public interface TeacherService extends IService<Teacher> {

    void pageQuery(Page<Teacher> pageParam, TeacherQuery teacherQuery);

}
