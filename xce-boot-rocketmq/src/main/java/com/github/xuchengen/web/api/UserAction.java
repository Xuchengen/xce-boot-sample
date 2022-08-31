package com.github.xuchengen.web.api;

import com.github.xuchengen.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-08-23 14:24
 **/
@RestController
@Api(value = "用户接口", tags = "用户接口")
public class UserAction {

    @Resource(name = "userService")
    private UserService userService;

    @PostMapping(value = "/createUser")
    @ApiOperation(value = "创建用户")
    public boolean createUser(@ApiParam(value = "姓名") @RequestParam String name,
                              @ApiParam(value = "邮箱") @RequestParam String email) {
        return userService.createUser(name, email);
    }

}
