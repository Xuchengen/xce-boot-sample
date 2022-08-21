package com.github.xuchengen.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 * 作者: 徐承恩<p>
 * 邮箱: <a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com</a><p>
 * 日期: 2022-08-15 14:27
 **/
@Controller
public class IndexAction {

    @GetMapping(value = "/")
    public String index() {
        return "redirect:doc.html";
    }

}
