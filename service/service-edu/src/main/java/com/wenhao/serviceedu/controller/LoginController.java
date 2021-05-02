package com.wenhao.serviceedu.controller;

import com.wenhao.commonutils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author Wenhao Tong
 * @Description
 * @create 2021-05-01 13:16
 */
@Api("用户管理")
@RestController
@RequestMapping("/serviceedu/user")
@CrossOrigin
public class LoginController {

    @PostMapping("/login")
    public R login(){
        return R.ok().data("token","admin");
    }

    @GetMapping("/info")
    public R info(){
        return R.ok()
                .data("roles","admin")
                .data("name","liuhaoran")
                .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif")
                .data("introduction","是我老公！");
    }
}
