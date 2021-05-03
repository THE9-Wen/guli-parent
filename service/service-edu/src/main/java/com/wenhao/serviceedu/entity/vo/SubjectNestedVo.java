package com.wenhao.serviceedu.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wenhao Tong
 * @Description
 * @create 2021-05-03 11:58
 */
@Data
public class SubjectNestedVo {
    private String id;
    private String title;
    private List<SubjectVo> children = new ArrayList<>();
}
