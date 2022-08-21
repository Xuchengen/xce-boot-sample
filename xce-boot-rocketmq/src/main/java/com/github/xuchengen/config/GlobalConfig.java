package com.github.xuchengen.config;

import cn.hutool.core.util.StrUtil;
import com.github.xuchengen.tool.DateTool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * <p>全局配置</p>
 * <p>作者：徐承恩</p>
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com</a></p>
 * <p>日期：2022-08-15 15:58</p>
 **/
@Configuration
public class GlobalConfig {

    /**
     * <p>将前台传递的日期字符串格式化成日期类型
     * <p>注意：仅适用于表单提交方式
     *
     * @return 返回日期类型
     */
    @Bean
    @SuppressWarnings("Convert2Lambda")
    public Converter<String, Date> string2Date() {
        return new Converter<String, Date>() {
            @Override
            @SuppressWarnings("NullableProblems")
            public Date convert(String source) {
                if (StrUtil.isNotBlank(source)) {
                    return DateTool.parse(source);
                }
                throw new IllegalArgumentException("参数错误导致字符串转时间类型异常");
            }
        };
    }

}
