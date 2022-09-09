package com.github.xuchengen.listener;

import com.github.xuchengen.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>初始化支付数据
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-09-02 17:20
 **/
@Component
public class InitPaymentDataListener implements ApplicationListener<ApplicationReadyEvent> {

    private final static Logger logger = LoggerFactory.getLogger(InitPaymentDataListener.class);

    @Resource(name = "paymentService")
    private PaymentService paymentService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        paymentService.initPaymentData();
    }
}
