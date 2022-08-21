package com.github.xuchengen.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>用户信息</p>
 * <p>作者：徐承恩</p>
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com</a></p>
 * <p>日期：2022-08-20 14:06</p>
 **/
@ApiModel(value = "用户信息")
public class UserVO {

    @ApiModelProperty(value = "主键", example = "123")
    private Integer id;
    @ApiModelProperty(value = "姓名", example = "徐承恩")
    private String name;
    @ApiModelProperty(value = "昵称", example = "思码老徐")
    private String niceName;
    @ApiModelProperty(value = "邮箱", example = "xuchengen@gmail.com")
    private String email;
    @ApiModelProperty(value = "余额", example = "999999999.99")
    private BigDecimal balance;

    @ApiModelProperty(value = "创建时间", example = "1660981732866")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNiceName() {
        return niceName;
    }

    public void setNiceName(String niceName) {
        this.niceName = niceName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", niceName='" + niceName + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                ", createTime=" + createTime +
                '}';
    }
}
