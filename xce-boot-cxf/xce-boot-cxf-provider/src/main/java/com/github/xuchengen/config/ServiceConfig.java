package com.github.xuchengen.config;

import com.github.xuchengen.api.HelloService;
import com.github.xuchengen.api.UserService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * <p>CXF提供者服务配置
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-08-21 10:00
 **/
@Configuration
public class ServiceConfig {

    @Autowired
    private Bus bus;

    @Bean
    public Endpoint helloEndpoint(HelloService helloService) {
        EndpointImpl endpoint = new EndpointImpl(bus, helloService);
        endpoint.publish("/hello");
        return endpoint;
    }

    @Bean
    public Endpoint userEndpoint(UserService userService) {
        EndpointImpl endpoint = new EndpointImpl(bus, userService);
        endpoint.publish("/user");
        return endpoint;
    }

}
