package com.wenhao.serviceedu.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.velocity.util.ArrayListWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wenhao Tong
 * @Description
 * @create 2021-05-03 21:30
 */
@Data
@ApiModel("章节信息")
public class ChapterVo {

    private static final long serialVersionUID = 1L;

    private String id;

    private String title;

    private List<VideoVo> children = new ArrayList<>();

}
