package com.github.xuchengen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * <p>启动类</p>
 * <p>作者：徐承恩</p>
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com</a></p>
 * <p>日期：2022-08-15 14:00</p>
 **/
@SpringBootApplication(scanBasePackages = "com.github.xuchengen")
@EnableAsync
@EnableScheduling
public class CxfProviderServer {

    public static void main(String[] args) {
        SpringApplication.run(CxfProviderServer.class, args);
    }

}
