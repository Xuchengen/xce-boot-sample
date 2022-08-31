package com.github.xuchengen.mq.tx;

import com.alibaba.fastjson.JSONObject;
import com.github.xuchengen.annotation.RocketMQTxTopic;
import com.github.xuchengen.consts.RocketMQTopic;
import com.github.xuchengen.dao.UserDORepository;
import com.github.xuchengen.dao.UserScoreDORepository;
import com.github.xuchengen.model.UserDO;
import com.github.xuchengen.model.UserScoreDO;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>用户创建事务
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-08-31 17:10
 **/
@Component
@RocketMQTxTopic(RocketMQTopic.USER_CREATE_FULL)
public class UserCreateTx implements RocketMQLocalTransactionListener {

    @Resource(name = "userDORepository")
    private UserDORepository userDORepository;

    @Resource(name = "userScoreDORepository")
    private UserScoreDORepository userScoreDORepository;

    @Transactional
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        try {
            // TODO 这里的消息荷载应该由框架帮我们反序列化才对啊
            UserDO userDO = JSONObject.parseObject((byte[]) msg.getPayload(), UserDO.class);
            Date currentDate = new Date();

            userDO.setCreateTime(currentDate);
            userDO.setUpdateTime(currentDate);
            userDORepository.save(userDO);

            UserScoreDO userScoreDO = new UserScoreDO();
            userScoreDO.setUid(userDO.getId());
            userScoreDO.setScore(0L);
            userScoreDO.setCreateTime(currentDate);
            userScoreDO.setUpdateTime(currentDate);
            userScoreDORepository.save(userScoreDO);
        } catch (Exception e) {
            e.printStackTrace();
            return RocketMQLocalTransactionState.ROLLBACK;
        }
        // 除非编程式事务，否则这里必须返回未知状态由回查来处理提交或回滚消息
        return RocketMQLocalTransactionState.UNKNOWN;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        UserDO userDO = JSONObject.parseObject((byte[]) msg.getPayload(), UserDO.class);
        UserDO _userDO = userDORepository.findByEmail(userDO.getEmail());
        if (_userDO == null) {
            return RocketMQLocalTransactionState.UNKNOWN;
        }
        return RocketMQLocalTransactionState.COMMIT;
    }
}
