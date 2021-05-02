package com.wenhao.commonbase.exceptionhandler;


import com.wenhao.commonbase.exception.MyException;
import com.wenhao.commonutils.R;
import com.wenhao.commonutils.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Wenhao Tong
 * @Description
 * @create 2021-04-30 16:55
 */
/*
    统一异常处理类
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public R error(Exception e){
//        e.printStackTrace();
//        return R.error();
//    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e){
        e.printStackTrace();
        log.error(ExceptionUtil.getMessage(e));
        return R.error().message("出现了算数异常");
    }

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public R error(MyException e){
        e.printStackTrace();
        log.error(ExceptionUtil.getMessage(e));
        return R.error().message("执行了自定义异常");
    }
}
