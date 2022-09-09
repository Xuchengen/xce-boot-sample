package com.github.xuchengen.service;

import cn.hutool.core.util.RandomUtil;
import com.github.xuchengen.dao.PaymentDoRepository;
import com.github.xuchengen.enums.PaymentStatusEnum;
import com.github.xuchengen.model.PaymentDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 * <p>出款服务
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-09-03 15:16
 **/
@Service
public class PaymentService {

    private final static Logger logger = LoggerFactory.getLogger(PaymentService.class);

    @Resource(name = "paymentDoRepository")
    private PaymentDoRepository paymentDoRepository;

    @Resource(name = "transactionManager")
    private PlatformTransactionManager transactionManager;

    public void initPaymentData() {
        TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        Date currentDate = new Date();
        ArrayList<PaymentDO> list = new ArrayList<>();
        try {
            paymentDoRepository.deleteAll();
            for (int i = 0; i < 5000; i++) {
                PaymentDO paymentDO = new PaymentDO();
                paymentDO.setAmount(RandomUtil.randomBigDecimal(new BigDecimal("99999")));
                paymentDO.setStatus(PaymentStatusEnum.WAIT.getCode());
                paymentDO.setCreateTime(currentDate);
                paymentDO.setUpdateTime(currentDate);
                list.add(paymentDO);
                if (list.size() % 500 == 0) {
                    paymentDoRepository.saveAll(list);
                    list.clear();
                }
            }
            transactionManager.commit(transactionStatus);
        } catch (Exception e) {
            transactionManager.rollback(transactionStatus);
            logger.warn("初始化支付流水数据异常！", e);
        }
    }

}
