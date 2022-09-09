package com.github.xuchengen.job;

import cn.hutool.core.lang.func.LambdaUtil;
import com.github.xuchengen.dao.PaymentDoRepository;
import com.github.xuchengen.enums.PaymentStatusEnum;
import com.github.xuchengen.job.listener.PaymentListener;
import com.github.xuchengen.job.processor.PaymentProcessor;
import com.github.xuchengen.job.reader.LoopReader;
import com.github.xuchengen.model.PaymentDO;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>串行出款任务配置
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-09-02 18:22
 **/
@Configuration
public class SerialPaymentJobConfig {

    @Resource(name = "paymentDoRepository")
    private PaymentDoRepository paymentDoRepository;

    @Resource(name = "paymentProcessor")
    private PaymentProcessor paymentProcessor;

    @Resource(name = "paymentListener")
    private PaymentListener paymentListener;

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job serialPaymentJob() {
        return jobBuilderFactory.get("SERIAL-PAYMENT-JOB")
                .listener(paymentListener)
                .start(step())
                .build();
    }

    private Step step() {
        return stepBuilderFactory.get("SERIAL-PAYMENT-JOB-STEP")
                .<PaymentDO, PaymentDO>chunk(100)
                .reader(reader())
                .processor(paymentProcessor)
                .writer(writer())
                .build();
    }

    private ItemReader<PaymentDO> reader() {
        PaymentDO paymentDO = new PaymentDO();
        paymentDO.setStatus(PaymentStatusEnum.WAIT.getCode());
        List<Example<PaymentDO>> params = new ArrayList<>();
        params.add(Example.of(paymentDO));

        LoopReader<PaymentDO> repositoryItemReader = new LoopReader<>();
        repositoryItemReader.setRepository(paymentDoRepository);
        repositoryItemReader.setPageSize(100);
        repositoryItemReader.setMethodName("findAll");
        repositoryItemReader.setSort(Collections
                .singletonMap(LambdaUtil.getFieldName(PaymentDO::getCreateTime), Sort.Direction.ASC));
        repositoryItemReader.setArguments(params);
        return repositoryItemReader;
    }

    private ItemWriter<PaymentDO> writer() {
        RepositoryItemWriter<PaymentDO> writer = new RepositoryItemWriter<>();
        writer.setRepository(paymentDoRepository);
        writer.setMethodName("updateStatus");
        return writer;
    }
}
