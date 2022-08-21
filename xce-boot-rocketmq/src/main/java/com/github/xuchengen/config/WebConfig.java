package com.github.xuchengen.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.HtmlUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>web配置</p>
 * <p>作者：徐承恩</p>
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com</a></p>
 * <p>日期：2022-08-20 21:33</p>
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        XssUrlHelper xssUrlHelper = new XssUrlHelper();
        configurer.setUrlPathHelper(xssUrlHelper);
    }

    static class XssUrlHelper extends UrlPathHelper {
        @Override
        @SuppressWarnings("NullableProblems")
        public Map<String, String> decodePathVariables(HttpServletRequest request,
                                                       Map<String, String> vars) {
            Map<String, String> result = super.decodePathVariables(request, vars);
            if (CollUtil.isNotEmpty(result)) {
                result.replaceAll((k, v) -> HtmlUtil.escape(v));
            }
            return result;
        }

        @Override
        @SuppressWarnings("NullableProblems")
        public MultiValueMap<String, String> decodeMatrixVariables(HttpServletRequest request,
                                                                   MultiValueMap<String, String> vars) {
            MultiValueMap<String, String> result = super.decodeMatrixVariables(request, vars);
            if (CollUtil.isNotEmpty(result)) {
                for (String key : result.keySet()) {
                    List<String> values = result.get(key);
                    values.replaceAll(HtmlUtil::escape);
                }
            }
            return result;
        }
    }
}
