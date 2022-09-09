package com.github.xuchengen.consts;

/**
 * <p>RocketMQ消息主题
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-08-31 17:14
 **/
public interface RocketMQTopic {

    // 用户主题
    String USER_TOPIC = "USER-TOPIC";

    // 创建用户标签
    String USER_CREATE_TAG = "USER-CREATE-TAG";

    // 创建用户完整TOPIC
    String USER_CREATE_FULL = USER_TOPIC + ":" + USER_CREATE_TAG;

    // 顺序消息标签
    String ORDERLY_TAG = "ORDERLY-TAG";

    // 顺序消息完整TOPIC
    String ORDERLY_FULL = USER_TOPIC + ":" + ORDERLY_TAG;

    // 顺序消息负载平衡标签
    String ORDERLY_BALANCE_TAG = "ORDERLY-BALANCE-TAG";

    // 顺序消息负载平衡完整TOPIC
    String ORDERLY_BALANCE_FULL = USER_TOPIC + ":" + ORDERLY_BALANCE_TAG;
}
