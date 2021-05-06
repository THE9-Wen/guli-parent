package com.wenhao.serviceedu.client.impl;

import com.wenhao.commonutils.R;
import com.wenhao.serviceedu.client.VodClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Wenhao Tong
 * @Description
 * @create 2021-05-05 21:14
 */
public class VodClientImpl implements VodClient {
    @Override
    public R removeVideo(String videoId) {
        return R.error().message("Time out!");
    }

    @Override
    public R removeVideoList(List<String> videoList) {
        return R.error().message("Time out!");
    }
}
