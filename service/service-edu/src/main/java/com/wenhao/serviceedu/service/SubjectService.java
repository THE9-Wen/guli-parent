package com.wenhao.serviceedu.service;

import com.wenhao.serviceedu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wenhao.serviceedu.entity.vo.SubjectNestedVo;
import com.wenhao.serviceedu.entity.vo.SubjectVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author wenhao Tong
 * @since 2021-05-03
 */
public interface SubjectService extends IService<Subject> {
    void batchImport(MultipartFile file);

    List<SubjectNestedVo> nestedList();
}
