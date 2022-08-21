package com.github.xuchengen.date;

import cn.hutool.core.util.StrUtil;
import com.github.xuchengen.tool.DateTool;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * <p>日期字符串转日期类型</p>
 * <p>作者：徐承恩</p>
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com</a></p>
 * <p>日期：2022-08-21 16:02</p>
 **/
public class DateStrToDateConverter implements Converter<String, Date> {
    @Override
    @SuppressWarnings("NullableProblems")
    public Date convert(String source) {
        if (StrUtil.isNotBlank(source)) {
            return DateTool.parse(source);
        }
        throw new IllegalArgumentException("参数错误导致字符串转时间类型异常");
    }
}
