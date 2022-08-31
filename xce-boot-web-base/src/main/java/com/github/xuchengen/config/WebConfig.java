package com.github.xuchengen.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.xuchengen.date.DateStrToDateConverter;
import com.github.xuchengen.date.JsonDateStrToDateDeserializer;
import com.github.xuchengen.xss.XssFilter;
import com.github.xuchengen.xss.XssJsonSerializer;
import com.github.xuchengen.xss.XssUrlHelper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Date;

/**
 * <p>
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-08-21 16:12
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public Converter<String, Date> dateStrToDateConverter() {
        return new DateStrToDateConverter();
    }

    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(String.class, new XssJsonSerializer());
        simpleModule.addDeserializer(Date.class, new JsonDateStrToDateDeserializer());
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }

    @Bean
    public FilterRegistrationBean<XssFilter> xssFilter() {
        FilterRegistrationBean<XssFilter> registration = new FilterRegistrationBean<>(new XssFilter());
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registration.addInitParameter("exclude", "/services/**");
        return registration;
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        XssUrlHelper xssUrlHelper = new XssUrlHelper();
        configurer.setUrlPathHelper(xssUrlHelper);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new DateStrToDateConverter());
    }
}
