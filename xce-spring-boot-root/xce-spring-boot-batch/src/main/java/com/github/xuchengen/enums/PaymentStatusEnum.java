package com.github.xuchengen.enums;

/**
 * <p>支付状态枚举
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-09-02 17:26
 **/
public enum PaymentStatusEnum {

    WAIT(1),
    SUCCESS(2);

    private Integer code;

    PaymentStatusEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
