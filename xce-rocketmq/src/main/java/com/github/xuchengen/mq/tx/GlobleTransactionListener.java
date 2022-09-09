package com.github.xuchengen.mq.tx;

import cn.hutool.extra.spring.SpringUtil;
import com.github.xuchengen.annotation.RocketMQTxTopic;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.apache.rocketmq.spring.support.RocketMQUtil;
import org.springframework.messaging.Message;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>全局事务消息监听器
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-08-23 14:53
 **/
@RocketMQTransactionListener
public class GlobleTransactionListener implements RocketMQLocalTransactionListener {

    private final Map<String, RocketMQLocalTransactionListener> txHandles = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        SpringUtil.getApplicationContext().getBeansWithAnnotation(RocketMQTxTopic.class).forEach((k, v) -> {
            RocketMQTxTopic annotation = v.getClass().getAnnotation(RocketMQTxTopic.class);
            String topic = annotation.value();
            txHandles.put(topic, (RocketMQLocalTransactionListener) v);
        });
    }

    private RocketMQLocalTransactionListener get(Message msg) {
        String topic = msg.getHeaders().get(RocketMQUtil.toRocketHeaderKey(RocketMQHeaders.TOPIC), String.class);
        String tag = msg.getHeaders().get(RocketMQUtil.toRocketHeaderKey(RocketMQHeaders.TAGS), String.class);
        RocketMQLocalTransactionListener rocketMQLocalTransactionListener = txHandles.get(topic + ":" + tag);
        if (Objects.isNull(rocketMQLocalTransactionListener)) {
            throw new RuntimeException("找不到事务处理器");
        }
        return rocketMQLocalTransactionListener;
    }

    // 执行本地事务
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        return get(msg).executeLocalTransaction(msg, arg);
    }

    // 本地事务状态回查
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        return get(msg).checkLocalTransaction(msg);
    }
}
