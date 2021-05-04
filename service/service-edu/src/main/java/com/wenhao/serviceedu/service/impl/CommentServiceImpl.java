package com.wenhao.serviceedu.service.impl;

import com.wenhao.serviceedu.entity.Comment;
import com.wenhao.serviceedu.mapper.CommentMapper;
import com.wenhao.serviceedu.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author wenhao Tong
 * @since 2021-05-03
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
