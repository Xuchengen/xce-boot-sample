package com.github.xuchengen.job.processor;

import cn.hutool.core.thread.ThreadUtil;
import com.github.xuchengen.enums.PaymentStatusEnum;
import com.github.xuchengen.model.PaymentDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <p>出款处理器
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-09-02 19:19
 **/
@Component
public class PaymentProcessor implements ItemProcessor<PaymentDO, PaymentDO> {

    private static final Logger logger = LoggerFactory.getLogger(PaymentProcessor.class);

    @Override
    public PaymentDO process(PaymentDO item) {
        String threadName = Thread.currentThread().getName();
        Long threadId = Thread.currentThread().getId();
        logger.info("出款理器线程：{}，{}", threadName, threadId);

        ThreadUtil.sleep(100);

        PaymentDO paymentDO = new PaymentDO();
        paymentDO.setId(item.getId());
        paymentDO.setStatus(PaymentStatusEnum.SUCCESS.getCode());
        paymentDO.setUpdateTime(new Date());
        return paymentDO;
    }
}
