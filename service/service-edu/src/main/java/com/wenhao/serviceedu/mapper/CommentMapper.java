package com.wenhao.serviceedu.mapper;

import com.wenhao.serviceedu.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 评论 Mapper 接口
 * </p>
 *
 * @author wenhao Tong
 * @since 2021-05-03
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
