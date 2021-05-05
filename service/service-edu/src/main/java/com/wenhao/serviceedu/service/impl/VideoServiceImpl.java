package com.wenhao.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wenhao.commonbase.exception.MyException;
import com.wenhao.serviceedu.client.VodClient;
import com.wenhao.serviceedu.entity.Video;
import com.wenhao.serviceedu.mapper.VideoMapper;
import com.wenhao.serviceedu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author wenhao Tong
 * @since 2021-05-03
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Resource
    @Lazy
    private VodClient vodClient;

    @Override
    public Integer getCountByChaprterId(String id) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",id);
        Integer integer = videoMapper.selectCount(queryWrapper);
        return integer;
    }

    @Override
    public boolean removeVideoById(String id) {
        Video video = getById(id);
        String videoSourceId = video.getVideoSourceId();
        //删除视频资源
        if(!StringUtils.isEmpty(videoSourceId)){
            vodClient.removeVideo(videoSourceId);
        }
        // 删除自己数据库里的视频信息
        Integer result = baseMapper.deleteById(id);
        return null != result && result > 0;
    }

    @Override
    public boolean removeByCourseId(String courseId) {
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",courseId);
        videoQueryWrapper.select("video_source_id");
        List<Video> list = list(videoQueryWrapper);

        List<String> videoIds = new ArrayList<>();
        for (Video video : list) {
            if (video.getVideoSourceId() != null)
                videoIds.add(video.getVideoSourceId());
        }

        vodClient.removeVideoList(videoIds);

        //删除video表示的记录
        QueryWrapper<Video> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("course_id", courseId);
        Integer count = baseMapper.delete(queryWrapper2);
        return null != count && count > 0;
    }

}
