package com.github.xuchengen.api;

import javax.jws.WebService;

/**
 * <p></p>
 * <p>作者：徐承恩</p>
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com</a></p>
 * <p>日期：2022-08-21 10:22</p>
 **/
@WebService
public interface HelloService {

    String sayHello(String name);

}
