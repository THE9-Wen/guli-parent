package com.wenhao.serviceedu.client;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.wenhao.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Wenhao Tong
 * @Description
 * @create 2021-05-05 14:57
 */
@FeignClient(value = "service-vod")
public interface VodClient {

    @SentinelResource("removeVideo")
    @DeleteMapping("/admin/video/{videoId}")
    R removeVideo(@PathVariable("videoId") String videoId);

    @SentinelResource("removeVideoList")
    @DeleteMapping("/delete-batch")
    R removeVideoList(@RequestParam("videoList") List<String> videoList);

}
