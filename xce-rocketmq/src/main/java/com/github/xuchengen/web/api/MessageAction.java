package com.github.xuchengen.web.api;

import cn.hutool.core.util.StrUtil;
import com.github.xuchengen.consts.RocketMQTopic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>消息队列测试
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-09-01 16:07
 **/
@RestController(value = "/message")
@Api(value = "消息队列", tags = "消息队列")
public class MessageAction {

    @Resource(name = "rocketMQTemplate")
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping(value = "/orderly")
    @ApiOperation(value = "顺序消息")
    public boolean orderly() {
        String uuid = StrUtil.uuid();
        for (int i = 0; i < 100; i++) {
            rocketMQTemplate.syncSendOrderly(RocketMQTopic.ORDERLY_FULL,
                    "老徐这是顺序消息：" + i, uuid);
        }
        return true;
    }

    @GetMapping(value = "/orderlyBalance")
    @ApiOperation(value = "顺序消息负载平衡")
    public boolean orderlyBalance() {
        String uuid = StrUtil.uuid();
        for (int i = 0; i < 100; i++) {
            rocketMQTemplate.syncSendOrderly(RocketMQTopic.ORDERLY_BALANCE_FULL,
                    "老徐这是顺序消息负载平衡：" + i, uuid);
        }
        return true;
    }
}
