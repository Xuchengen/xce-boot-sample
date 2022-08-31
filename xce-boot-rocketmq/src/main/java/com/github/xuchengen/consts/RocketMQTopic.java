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

}
