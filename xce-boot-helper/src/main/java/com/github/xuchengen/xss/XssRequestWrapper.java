package com.github.xuchengen.xss;

import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * <p>XSS攻击请求包装器</p>
 * <p>作者：徐承恩</p>
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com</a></p>
 * <p>日期：2022-08-20 15:20</p>
 **/
public class XssRequestWrapper extends HttpServletRequestWrapper {

    public XssRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = replace(values[i]);
        }
        return encodedValues;
    }

    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        return replace(value);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return replace(value);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        List<String> result = new ArrayList<>();
        Enumeration<String> headers = super.getHeaders(name);
        while (headers.hasMoreElements()) {
            String header = headers.nextElement();
            String[] tokens = header.split(",");
            for (String token : tokens) {
                result.add(replace(token));
            }
        }
        return Collections.enumeration(result);
    }

    private static String replace(String targetStr) {
        if (targetStr == null) {
            return null;
        }
        return HtmlUtils.htmlEscape(targetStr);
    }
}
