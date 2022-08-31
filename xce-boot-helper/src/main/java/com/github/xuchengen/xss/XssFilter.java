package com.github.xuchengen.xss;

import cn.hutool.core.text.AntPathMatcher;
import cn.hutool.core.util.StrUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>XSS攻击过滤器
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-08-20 15:25
 **/

public class XssFilter implements Filter {

    private final List<String> exclude = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) {
        String excludedStr = filterConfig.getInitParameter("exclude");
        exclude.addAll(StrUtil.split(excludedStr, ";"));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest _request = (HttpServletRequest) request;
        String requestURI = _request.getRequestURI();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        for (String str : exclude) {
            if (antPathMatcher.match(str, requestURI)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        filterChain.doFilter(new XssRequestWrapper((HttpServletRequest) request), response);
    }
}
