package com.github.xuchengen.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-09-02 20:12
 **/
@Controller
public class IndexAction {

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

}
