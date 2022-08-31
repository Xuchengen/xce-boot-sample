package com.github.xuchengen.mq.listener;

import com.github.xuchengen.consts.RocketMQTopic;
import com.github.xuchengen.dao.UserDORepository;
import com.github.xuchengen.model.UserDO;
import com.github.xuchengen.service.UserScoreService;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>用户创建消息监听
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-08-23 15:42
 **/
@Component
@RocketMQMessageListener(consumerGroup = "test",
        topic = RocketMQTopic.USER_TOPIC,
        selectorExpression = RocketMQTopic.USER_CREATE_TAG)
public class UserCreateConsumerListener implements RocketMQListener<UserDO> {

    @Resource(name = "userDORepository")
    private UserDORepository userDORepository;

    @Resource(name = "userScoreService")
    private UserScoreService userScoreService;

    @Override
    public void onMessage(UserDO message) {
        UserDO userDO = userDORepository.findByEmail(message.getEmail());
        userScoreService.incrScore(userDO.getId(), 9999L);
    }
}
