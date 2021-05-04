package com.wenhao.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wenhao.commonbase.exception.MyException;
import com.wenhao.serviceedu.entity.Chapter;
import com.wenhao.serviceedu.entity.Video;
import com.wenhao.serviceedu.entity.vo.ChapterVo;
import com.wenhao.serviceedu.entity.vo.VideoVo;
import com.wenhao.serviceedu.mapper.ChapterMapper;
import com.wenhao.serviceedu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenhao.serviceedu.service.VideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author wenhao Tong
 * @since 2021-05-03
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private ChapterMapper chapterMapper;

    @Autowired
    private VideoService videoService;

    @Override
    public List<ChapterVo> nestedList(String courseId) {

        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id",courseId);

        List<Chapter> chapters = chapterMapper.selectList(chapterQueryWrapper);

        List<ChapterVo> list = new ArrayList<>();

        for (Chapter chapter : chapters) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter,chapterVo);
            QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
            videoQueryWrapper.eq("chapter_id",chapter.getId());
            List<Video> videos = videoService.list(videoQueryWrapper);
            List<VideoVo> videoVos = new ArrayList<>();
            for (Video video : videos) {
                VideoVo videoVo = new VideoVo();
                BeanUtils.copyProperties(video,videoVo);
                videoVos.add(videoVo);
            }
            chapterVo.setChildren(videoVos);
            list.add(chapterVo);
        }

        return list;
    }

    @Override
    public boolean removeChapterById(String id) {
        Integer countByChaprterId = videoService.getCountByChaprterId(id);
        if (countByChaprterId == 0) {
            int i = chapterMapper.deleteById(id);
            return i == 1;
        } else {
            throw new MyException(20001,"该章节还有视频未删除。");
        }
    }
}
