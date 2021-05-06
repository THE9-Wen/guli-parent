package com.wenhao.servicecms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wenhao.servicecms.entity.Banner;
import com.wenhao.servicecms.entity.query.BannerQuery;
import com.wenhao.servicecms.mapper.BannerMapper;
import com.wenhao.servicecms.service.BannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author wenhao Tong
 * @since 2021-05-06
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public List<Banner> selectIndexList() {
        //根据id进行降序排列，显示排列之后前两条记录
        QueryWrapper<Banner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        //last方法，拼接sql语句
        wrapper.last("limit 2");
        return bannerMapper.selectList(wrapper);
    }
}
