package com.wenhao.servicecms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
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
 * @author Wenhao Tong
 * @Description
 * @create 2021-05-06 22:53
 */
@RestController
@RequestMapping("/admin/banner")
@CrossOrigin
public class AdminBannerController {
    @Autowired
    private BannerService bannerService;

    @ApiOperation(value = "获取Banner分页列表")
    @GetMapping("{page}/{limit}")
    public R index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit){
        Page<Banner> bannerPage = new Page<>(page,limit);
        bannerService.page(bannerPage, null);
        List<Banner> records = bannerPage.getRecords();
        return R.ok().data("items",records).data("total",bannerPage.getTotal());
    }

    @ApiOperation(value = "添加Banner")
    @PostMapping
    public R save(@RequestBody Banner banner){
        bannerService.save(banner);
        return R.ok();
    }

    @ApiOperation(value = "根据Id查询Banner")
    @GetMapping("{id}")
    public R getById(@PathVariable String id){
        Banner banner = bannerService.getById(id);
        return R.ok().data("item",banner);
    }

    @ApiOperation(value = "根据Id删除Banner")
    @DeleteMapping("{id}")
    public R deleteById(@PathVariable String id){
        bannerService.removeById(id);
        return R.ok();
    }

    @ApiOperation(value = "根据Id删除Banner")
    @PutMapping("{id}")
    public R updateById(
            @PathVariable String id,
            @RequestBody Banner banner){
        banner.setId(id);
        bannerService.updateById(banner);
        return R.ok();
    }


}
