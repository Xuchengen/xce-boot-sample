package com.github.xuchengen.config;

import cn.hutool.core.lang.Assert;
import com.github.xuchengen.api.HelloService;
import com.github.xuchengen.api.UserService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p></p>
 * <p>作者：徐承恩</p>
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com</a></p>
 * <p>日期：2022-08-21 10:38</p>
 **/
@Configuration
public class ServiceConfig {

    @Bean
    public HelloService helloService(@Value(value = "${service.address}")
                                     String address) {
        Assert.notBlank(address, "Hello服务地址为空");
        return createFacade(HelloService.class, address, "/hello");
    }

    @Bean
    public UserService userService(@Value(value = "${service.address}")
                                   String address) {
        Assert.notBlank(address, "User服务地址为空");
        return createFacade(UserService.class, address, "/user");
    }

    /**
     * 创建Facade
     *
     * @param clazz          具体类型
     * @param serviceAddress 服务地址
     * @param servicePath    接口路径
     * @param <T>            具体的Facade类
     * @return T
     */
    private <T> T createFacade(Class<T> clazz, String serviceAddress, String servicePath) {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setAddress(serviceAddress + servicePath);
        jaxWsProxyFactoryBean.setServiceClass(clazz);
        return jaxWsProxyFactoryBean.create(clazz);
    }

}
