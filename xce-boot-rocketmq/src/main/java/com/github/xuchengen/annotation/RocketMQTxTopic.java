package com.github.xuchengen.annotation;

import java.lang.annotation.*;

/**
 * <p>RocketMQ事务主题注解
 * <p>用于事务消息监听器处理本地事务以及回查阶段的分发
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-08-31 17:05
 **/
@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RocketMQTxTopic {

    String value();

}
