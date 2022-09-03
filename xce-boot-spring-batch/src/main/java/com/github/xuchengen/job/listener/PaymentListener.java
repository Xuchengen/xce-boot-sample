package com.github.xuchengen.job.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobInstance;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <p>
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-09-03 16:14
 **/
@Component
public class PaymentListener implements JobExecutionListener {

    private static final Logger logger = LoggerFactory.getLogger(PaymentListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.info("Job启动：{}", jobExecution.getJobInstance().getJobName());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        JobInstance jobInstance = jobExecution.getJobInstance();
        Date startTime = jobExecution.getStartTime();
        Date endTime = jobExecution.getEndTime();
        long time = endTime.getTime() - startTime.getTime();
        logger.info("Job执行完毕：{}，耗时：{}秒", jobInstance.getJobName(), time / 1000);
    }
}
