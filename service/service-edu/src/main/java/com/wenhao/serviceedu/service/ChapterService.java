package com.wenhao.serviceedu.service;

import com.wenhao.serviceedu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wenhao.serviceedu.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author wenhao Tong
 * @since 2021-05-03
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> nestedList(String courseId);

    boolean removeChapterById(String id);
}
