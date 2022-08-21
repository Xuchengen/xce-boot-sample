package com.github.xuchengen.xss;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.HtmlUtil;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>XSS攻击处理URL路径中携带变量</p>
 * <p>作者：徐承恩</p>
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com</a></p>
 * <p>日期：2022-08-21 15:19</p>
 **/
public class XssUrlHelper extends UrlPathHelper {

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
