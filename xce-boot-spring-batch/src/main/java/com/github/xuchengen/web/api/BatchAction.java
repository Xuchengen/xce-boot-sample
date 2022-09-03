package com.github.xuchengen.web.api;

import cn.hutool.core.date.DateTime;
import com.github.xuchengen.service.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>批量出款
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-09-02 20:14
 **/
@RestController
@RequestMapping(value = "/batch")
@Api(value = "批量出款服务", tags = "批量出款服务")
public class BatchAction {

    private static final Logger logger = LoggerFactory.getLogger(BatchAction.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Resource(name = "concurrencyPaymentJob")
    private Job concurrencyPaymentJob;

    @Resource(name = "serialPaymentJob")
    private Job serialPaymentJob;

    @Resource(name = "paymentService")
    private PaymentService paymentService;

    @Resource(name = "applicationTaskExecutor")
    private ThreadPoolTaskExecutor taskExecutor;

    @GetMapping(value = "/serialPayment")
    @ApiOperation(value = "串行批量出款")
    public boolean serialPayment() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addDate("timestamp", new DateTime())
                    .toJobParameters();
            taskExecutor.execute(() -> {
                try {
                    jobLauncher.run(serialPaymentJob, jobParameters);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("launch job exception ", e);
        }
        return true;
    }

    @GetMapping(value = "/concurrencyPayment")
    @ApiOperation(value = "并发批量出款")
    public boolean concurrencyPayment() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addDate("timestamp", new DateTime())
                    .toJobParameters();
            taskExecutor.execute(() -> {
                try {
                    jobLauncher.run(concurrencyPaymentJob, jobParameters);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("launch job exception ", e);
        }
        return true;
    }

    @GetMapping(value = "/genData")
    @ApiOperation(value = "生成数据")
    public boolean genData() {
        paymentService.initPaymentData();
        return true;
    }

}
