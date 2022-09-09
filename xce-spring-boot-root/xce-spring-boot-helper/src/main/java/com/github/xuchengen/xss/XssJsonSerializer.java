package com.github.xuchengen.xss;

import cn.hutool.http.HtmlUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * <p>XSS攻击JSON序列化器
 * <p>注意：仅适用于RequestBody请求方式
 * <p>将请求体中特殊字符进行转义防止xss攻击
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-08-21 15:23
 **/
public class XssJsonSerializer extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        return HtmlUtil.escape(jsonParser.getText());
    }
}
