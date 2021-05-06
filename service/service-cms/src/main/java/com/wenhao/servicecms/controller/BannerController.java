package com.wenhao.servicecms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wenhao.commonutils.R;
import com.wenhao.servicecms.entity.Banner;
import com.wenhao.servicecms.service.BannerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author wenhao Tong
 * @since 2021-05-06
 */
@RestController
@RequestMapping("/cms/banner")
@CrossOrigin
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @ApiOperation(value = "获取首页banner")
    @GetMapping("getAllBanner")
    public R index() {
        List<Banner> list = bannerService.selectIndexList();
        return R.ok().data("bannerList", list);
    }

}

