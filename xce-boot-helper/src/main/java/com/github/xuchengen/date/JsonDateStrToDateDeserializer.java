package com.github.xuchengen.date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.github.xuchengen.tool.DateTool;

import java.io.IOException;
import java.util.Date;

/**
 * <p>JSON日期类型字符串转日期反序列化器</p>
 * <p>注意：仅适用于RequestBody请求方式</p>
 * <p>将请求体中符合时间格式的字符串转换成日期类型</p>
 * <p>作者：徐承恩</p>
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com</a></p>
 * <p>日期：2022-08-21 15:28</p>
 **/
public class JsonDateStrToDateDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        return DateTool.parse(jsonParser.getText());
    }
}
