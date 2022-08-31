package com.github.xuchengen.service.impl;

import com.github.xuchengen.api.UserService;
import com.github.xuchengen.model.UserVO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-08-21 10:28
 **/
@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserVO getUserInfo(String id) {
        UserVO userVO = new UserVO();
        userVO.setId(9999);
        userVO.setName("徐承恩");
        userVO.setNiceName("思码老徐");
        userVO.setEmail("xuchengen@gmail.com");
        userVO.setBalance(new BigDecimal("999999999"));
        userVO.setCreateTime(new Date());
        return userVO;
    }
}
