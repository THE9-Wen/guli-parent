package com.wenhao.serviceedu.mapper;

import com.wenhao.serviceedu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wenhao.serviceedu.entity.vo.CoursePublishVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author wenhao Tong
 * @since 2021-05-03
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    CoursePublishVo selectCoursePublishVoById(String id);

}
