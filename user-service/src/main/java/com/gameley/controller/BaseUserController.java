package com.gameley.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lw
 * @since 2017-12-25
 */
@RestController
@RequestMapping("/baseUser")
public class BaseUserController {

    @ApiOperation(value = "测试文档接口",notes = "")
    @GetMapping("/hi")
    public String hi(){
        return "hello world";
    }
}
