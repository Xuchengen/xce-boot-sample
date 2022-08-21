package com.github.xuchengen.config;

import cn.hutool.http.HtmlUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.github.xuchengen.tool.DateTool;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.Date;

/**
 * <p>JSON转换配置</p>
 * <p>注意：仅适用于RequestBody请求方式</p>
 * <p>作者：徐承恩</p>
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com</a></p>
 * <p>日期：2022-08-15 15:40</p>
 **/
@JsonComponent
public class JSONConvertConfig {

    /**
     * 将请求体中符合时间格式的字符串转换成日期类型
     */
    public static class DateJsonSerializer extends JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonParser jsonParser, DeserializationContext context)
                throws IOException {
            return DateTool.parse(jsonParser.getText());
        }
    }

    /**
     * 将请求体中特殊字符进行转义防止xss攻击
     */
    public static class XssJsonSerializer extends JsonDeserializer<String> {

        @Override
        public String deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
            return HtmlUtil.escape(jsonParser.getText());
        }
    }
}
