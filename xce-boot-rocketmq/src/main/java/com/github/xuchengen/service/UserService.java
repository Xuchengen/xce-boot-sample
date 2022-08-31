package com.github.xuchengen.service;

import cn.hutool.json.JSONUtil;
import com.github.xuchengen.consts.RocketMQTopic;
import com.github.xuchengen.model.UserDO;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>用户服务
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-08-23 14:26
 **/
@Service
public class UserService {

    @Resource(name = "rocketMQTemplate")
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 创建用户
     *
     * @param name  姓名
     * @param email 邮箱
     * @return 布尔值
     */
    public boolean createUser(String name, String email) {
        UserDO userDO = new UserDO();
        userDO.setName(name);
        userDO.setEmail(email);
        TransactionSendResult result = rocketMQTemplate.sendMessageInTransaction(RocketMQTopic.USER_CREATE_FULL,
                MessageBuilder.withPayload(JSONUtil.toJsonStr(userDO)).build(), null);
        return result.getSendStatus().equals(SendStatus.SEND_OK);
    }

}
