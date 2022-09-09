package com.github.xuchengen.service;

import com.github.xuchengen.dao.UserScoreDORepository;
import com.github.xuchengen.model.UserScoreDO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * <p>用户积分服务
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-08-31 18:59
 **/
@Service
public class UserScoreService {

    @Resource(name = "userScoreDORepository")
    private UserScoreDORepository userScoreDORepository;

    /**
     * 增加积分
     *
     * @param uid   用户ID
     * @param score 积分
     */
    @Transactional
    public void incrScore(Long uid, Long score) {
        UserScoreDO userScoreDO = userScoreDORepository.findByUid(uid);
        if (Objects.nonNull(userScoreDO)) {
            userScoreDORepository.updateScore(userScoreDO.getId(), score);
        }
    }

}
