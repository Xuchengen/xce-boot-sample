package com.github.xuchengen.dao;

import com.github.xuchengen.model.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>用户表持久化操作类
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-08-23 15:16
 **/
@Repository
public interface UserDORepository extends JpaRepository<UserDO, Long> {

    UserDO findByEmail(String email);

}
