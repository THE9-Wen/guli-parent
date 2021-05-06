package com.wenhao.servicecms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wenhao.servicecms.entity.Banner;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wenhao.servicecms.entity.query.BannerQuery;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author wenhao Tong
 * @since 2021-05-06
 */
public interface BannerService extends IService<Banner> {

    List<Banner> selectIndexList();
}
