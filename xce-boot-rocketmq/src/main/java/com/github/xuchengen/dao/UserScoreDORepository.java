package com.github.xuchengen.dao;

import com.github.xuchengen.model.UserScoreDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>用户积分表持久化操作类
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-08-31 18:56
 **/
@Repository
public interface UserScoreDORepository extends JpaRepository<UserScoreDO, Long> {

    UserScoreDO findByUid(Long uid);

    @Modifying
    @Query(value = "update t_user_score t " +
            "set t.score = t.score + :score , t.updateTime = now() " +
            "where t.id = :id and t.score >= 0")
    int updateScore(@Param("id") Long id, @Param("score") Long score);
}
