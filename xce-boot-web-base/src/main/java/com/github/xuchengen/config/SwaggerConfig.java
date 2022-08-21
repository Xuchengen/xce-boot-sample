package com.github.xuchengen.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>接口文档配置</p>
 * <p>作者：徐承恩</p>
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com</a></p>
 * <p>日期：2022-08-15 17:25</p>
 **/
@EnableSwagger2
@Configuration
@EnableKnife4j
public class SwaggerConfig {

    @Bean(value = "dockerBean")
    public Docket dockerBean() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("思码老徐接口文档")
                        .description("# 思码老徐自吹自擂的项目")
                        .termsOfServiceUrl("https://xuchengen.cn/")
                        .contact(new Contact("徐承恩", "https://xuchengen.cn/", "xuchengen@gmail.com"))
                        .version("1.0")
                        .build())
                .globalRequestParameters(setGlobalParameter())
                //分组名称
                .groupName("用户服务")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.github.xuchengen.web.api"))
                .paths(PathSelectors.any())
                .build();
    }

    private List<RequestParameter> setGlobalParameter() {
        ArrayList<RequestParameter> requestParameters = new ArrayList<>();
        requestParameters.add(loginToken());
        return requestParameters;
    }

    private RequestParameter loginToken() {
        return new RequestParameterBuilder()
                .name("x-token")
                .description("登录令牌")
                .in(ParameterType.HEADER)
                .query(q -> q.defaultValue("eyJyb2xlIjoiQ0FORElEQVRFIiwic3ViIjoiMTIzIn0=")
                        .model(m -> m.scalarModel(ScalarType.STRING)))
                .required(true)
                .build();
    }

}
