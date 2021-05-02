package com.wenhao.serviceoss.controller;

import com.wenhao.commonutils.R;
import com.wenhao.serviceoss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Wenhao Tong
 * @Description
 * @create 2021-05-02 14:31
 */
@Api("阿里云文件管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/admin/file")
public class FileUpLoadController {

    @Autowired
    private FileService fileService;

    /**
     * 文件上传
     *
     * @param file
     */
    @ApiOperation(value = "文件上传")
    @PostMapping("/upload")
    public R upload(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) {
        String uploadUrl = fileService.upload(file);
        return R.ok().message("文件上传成功！").data("url",uploadUrl);
    }

    @Value("${server.port}")
    private String port;

    @GetMapping("/port")
    public R port(){
        return R.ok().data("port",port);
    }

}
