package com.github.xuchengen.service.impl;

import com.github.xuchengen.api.HelloService;
import org.springframework.stereotype.Service;

/**
 * <p></p>
 * <p>作者：徐承恩</p>
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com</a></p>
 * <p>日期：2022-08-21 9:28</p>
 **/
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
