package com.wenhao.serviceedu.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Wenhao Tong
 * @Description
 * @create 2021-05-03 21:32
 */
@Data
public class VideoVo {
    private static final long serialVersionUID = 1L;

    private String id;

    private String title;

    private boolean isFree;
}
