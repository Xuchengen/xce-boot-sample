package com.github.xuchengen.web;

import cn.hutool.core.map.MapUtil;
import com.github.xuchengen.api.HelloService;
import com.github.xuchengen.api.UserService;
import com.github.xuchengen.model.UserVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-08-21 10:44
 **/
@RestController
public class IndexAction {

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "helloService")
    private HelloService helloService;

    @GetMapping(value = "/")
    public Object index() {
        UserVO userInfo = userService.getUserInfo("99999");
        String hello = helloService.sayHello("徐承恩");
        return MapUtil.builder()
                .put("getUserInfo", userInfo)
                .put("sayHello", hello)
                .build();
    }

}
