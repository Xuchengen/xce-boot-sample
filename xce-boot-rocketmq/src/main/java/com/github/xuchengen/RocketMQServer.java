package com.github.xuchengen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <p>启动类
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-08-15 14:00
 **/
@SpringBootApplication(scanBasePackages = "com.github.xuchengen")
@EnableAsync
@EnableScheduling
@EnableTransactionManagement
public class RocketMQServer {
    public static void main(String[] args) {
        SpringApplication.run(RocketMQServer.class, args);
    }

}
