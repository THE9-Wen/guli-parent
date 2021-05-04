package com.wenhao.serviceedu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wenhao.serviceedu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wenhao.serviceedu.entity.CourseForm;
import com.wenhao.serviceedu.entity.query.CourseQuery;
import com.wenhao.serviceedu.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author wenhao Tong
 * @since 2021-05-03
 */
public interface CourseService extends IService<Course> {

    CoursePublishVo getCoursePublishVoById(String id);

    void pageQuery(Page<Course> pageParam, CourseQuery courseQuery);

    String saveCourseInfo(CourseForm courseForm);

    boolean updateCourseInfo(CourseForm courseForm);

    CourseForm getCourseFormById(String id);

    boolean publishCourseById(String id);
}
