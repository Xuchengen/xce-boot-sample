package com.github.xuchengen.web.api;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;
import com.github.xuchengen.model.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * <p>用户接口
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-08-15 17:51
 **/
@RestController
@RequestMapping("/api/user")
@Api(tags = "用户服务")
public class UserAction {

    @GetMapping(value = "/getUserInfo")
    @ApiOperation(value = "获取用户信息")
    public UserVO getName() {
        UserVO userVO = new UserVO();
        userVO.setId(9999);
        userVO.setName("徐承恩");
        userVO.setNiceName("思码老徐");
        userVO.setEmail("xuchengen@gmail.com");
        userVO.setBalance(new BigDecimal("999999999"));
        userVO.setCreateTime(new Date());
        return userVO;
    }

    @PostMapping(value = "/createUser")
    @ApiOperation(value = "创建用户")
    public UserVO createUser(UserVO userVO) {
        System.out.println(JSONUtil.toJsonStr(userVO));
        return userVO;
    }


    @PostMapping(value = "/createMember")
    @ApiOperation(value = "创建会员")
    public UserVO createMember(@RequestBody UserVO userVO) {
        System.out.println(JSONUtil.toJsonStr(userVO));
        return userVO;
    }

    @GetMapping(value = "/getOther/{name}/{age}/{balance}")
    @ApiOperation(value = "获取其它信息")
    public Map<Object, Object> getOther(@PathVariable String name,
                                        @PathVariable Integer age,
                                        @PathVariable BigDecimal balance) {
        return MapUtil.builder()
                .put("name", name)
                .put("age", age)
                .put("balance", balance)
                .build();
    }

    @GetMapping(value = "/getOther2")
    @ApiOperation(value = "获取其它信息2")
    public Map<Object, Object> getOther2(@RequestParam String name,
                                         @RequestParam Integer age,
                                         @RequestParam BigDecimal balance) {
        return MapUtil.builder()
                .put("name", name)
                .put("age", age)
                .put("balance", balance)
                .build();
    }
}
