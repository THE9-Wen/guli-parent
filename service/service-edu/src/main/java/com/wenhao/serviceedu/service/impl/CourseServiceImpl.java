package com.wenhao.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wenhao.commonbase.exception.MyException;
import com.wenhao.serviceedu.entity.Course;
import com.wenhao.serviceedu.entity.CourseDescription;
import com.wenhao.serviceedu.entity.CourseForm;
import com.wenhao.serviceedu.entity.query.CourseQuery;
import com.wenhao.serviceedu.entity.vo.CoursePublishVo;
import com.wenhao.serviceedu.mapper.CourseDescriptionMapper;
import com.wenhao.serviceedu.mapper.CourseMapper;
import com.wenhao.serviceedu.service.ChapterService;
import com.wenhao.serviceedu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenhao.serviceedu.service.VideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author wenhao Tong
 * @since 2021-05-03
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseDescriptionMapper courseDescriptionMapper;

    @Autowired
    private VideoService videoService;

    @Autowired
    private ChapterService chapterService;

    @Override
    public void pageQuery(Page<Course> pageParam, CourseQuery courseQuery) {
        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();

        if (courseQuery == null) {
            courseMapper.selectMapsPage(pageParam, courseQueryWrapper);
            return;
        }

        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();

        if (title != null) {
            courseQueryWrapper.like("title",title);
        }

        if (status != null) {
            courseQueryWrapper.eq("status",status);
        }
        courseMapper.selectMapsPage(pageParam, courseQueryWrapper);
    }

    public boolean updateCourseInfo(CourseForm courseForm){
        //保存课程基本信息
        Course course = new Course();
        course.setStatus(Course.COURSE_DRAFT);
        BeanUtils.copyProperties(courseForm,course);
        int update1 = courseMapper.updateById(course);
        if (update1 == 0) throw new MyException(20001,"课程基本信息更新失败！");

        //保存课程详情信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseForm.getDescription());
        courseDescription.setId(course.getId());
        int update2 = courseDescriptionMapper.updateById(courseDescription);
        if (update2 == 0) throw new MyException(20001,"课程描述信息更新失败！");
        return true;
    };

    @Override
    public String saveCourseInfo(CourseForm courseForm) {
        //保存课程基本信息
        Course course = new Course();
        course.setStatus(Course.COURSE_DRAFT);
        BeanUtils.copyProperties(courseForm,course);
        boolean insert = save(course);
        if (!insert) throw new MyException(20001,"课程基本信息保存失败！");

        //保存课程详情信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseForm.getDescription());
        courseDescription.setId(course.getId());
        int insert1 = courseDescriptionMapper.insert(courseDescription);
        if (insert1 == 0) throw new MyException(20001,"课程描述信息保存失败！");
        return course.getId();
    }

    @Override
    public CourseForm getCourseFormById(String id) {

        Course course = courseMapper.selectById(id);
        if (course == null) throw new MyException(20001,"数据不存在!");
        CourseDescription courseDescription = courseDescriptionMapper.selectById(id);
        if (courseDescription == null) throw new MyException(20001,"course和course_description两张表的数据不一致！");

        CourseForm courseForm = new CourseForm();

        BeanUtils.copyProperties(course,courseForm);
        courseForm.setDescription(courseDescription.getDescription());

        return courseForm;
    }

    @Override
    public boolean publishCourseById(String id) {
        Course course = new Course();
        course.setId(id);
        course.setStatus(Course.COURSE_NORMAL);

        int i = courseMapper.updateById(course);
        return i == 1;
    }

    @Override
    public boolean removeCourseById(String id) {
        videoService.removeByCourseId(id);

        chapterService.removeByCourseId(id);

        courseDescriptionMapper.deleteById(id);

        Integer result = courseMapper.deleteById(id);

        return null != result && result > 0;
    }

    public CoursePublishVo getCoursePublishVoById(String id){
        return courseMapper.selectCoursePublishVoById(id);
    }
}
