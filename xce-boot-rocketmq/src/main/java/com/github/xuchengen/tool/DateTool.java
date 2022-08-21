package com.github.xuchengen.tool;

import cn.hutool.core.date.DateException;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;

/**
 * <p>日期工具</p>
 * <p>作者：徐承恩</p>
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com</a></p>
 * <p>日期：2022-08-20 19:40</p>
 **/
public abstract class DateTool {

    /**
     * <p>最小Unix时间戳
     * <p>代表：0001-01-01
     */
    private static final long MIN_UNIX_TIMESTAMP = -62135798400000L;

    /**
     * <p>最大Unix时间戳
     * <p>代表：9999-12-31
     */
    private static final long MAX_UNIX_TIMESTAMP = 253402185600000L;

    /**
     * 增强字符串转日期方法使其支持Unix字符串转日期
     *
     * @param dateStr 日期字符串
     * @return 日期
     */
    public static DateTime parse(String dateStr) {
        try {
            return DateUtil.parse(dateStr);
        } catch (DateException e) {
            if (NumberUtil.isNumber(dateStr)) {
                long timestamp = NumberUtil.parseLong(dateStr);
                if (timestamp >= MIN_UNIX_TIMESTAMP && timestamp <= MAX_UNIX_TIMESTAMP) {
                    return new DateTime(timestamp);
                }
            }
        }

        throw new DateException("No format fit for date String [{}] !", dateStr);
    }

}
