package com.wenhao.serviceedu.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Wenhao Tong
 * @Description
 * @create 2021-05-04 10:48
 */
@Data
@ApiModel("课程发布信息")
public class CoursePublishVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;

    private String cover;

    private Integer lessonNum;

    private String oneSubject;

    private String twoSubject;

    private String teacher;

    private String price;
}
