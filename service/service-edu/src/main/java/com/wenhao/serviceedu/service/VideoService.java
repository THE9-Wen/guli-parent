package com.wenhao.serviceedu.service;

import com.wenhao.serviceedu.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wenhao.serviceedu.entity.vo.VideoVo;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author wenhao Tong
 * @since 2021-05-03
 */
public interface VideoService extends IService<Video> {
    Integer  getCountByChaprterId(String id);
}
