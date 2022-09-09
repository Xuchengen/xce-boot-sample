package com.github.xuchengen.api;

import javax.jws.WebService;

/**
 * <p>
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-08-21 10:22
 **/
@WebService
public interface HelloService {

    String sayHello(String name);

}
