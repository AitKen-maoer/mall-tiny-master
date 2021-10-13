package com.macro.mall.tiny.controller;

import com.macro.mall.tiny.common.api.CommonResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "UserController", description = "前台用户服务接口")
@RequestMapping("/user")
public class UserController {


    /**
     * 前台登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public CommonResult login(@RequestParam(value = "username") String username,
                              @RequestParam(value = "password") String password){
        //TODO
        return null;
    }
}
