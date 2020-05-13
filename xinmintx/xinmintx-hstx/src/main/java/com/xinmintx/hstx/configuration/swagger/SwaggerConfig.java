package com.xinmintx.hstx.configuration.swagger;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2020/3/13 0013
 * @time: 下午 13:42
 * @Description: Swagger2配置
 */
@Configuration
@EnableSwagger2
@Profile("dev")//只在dev配置文件生效
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder.name("token").description("用户token").modelRef(new ModelRef("string")).parameterType("header").required(true).build();
        List<Parameter> aParameters = Lists.newArrayList();
        aParameters.add(aParameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(aParameters)
                .apiInfo(apiInfo())
                .select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.xinmintx.hstx.controller"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(security());
    }

    private List<ApiKey> security() {
        List<ApiKey> apiKeys = new ArrayList<>();
        ApiKey apiKey = new ApiKey("token", "token", "header");
        apiKeys.add(apiKey);
        return apiKeys;
    }

    /**
     * 摘要信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("惠商天下接口文档")
                //创建人
                .contact(new Contact("wcj", null, null))
                //版本号
                .version("版本号:1.0")
                //描述
                .description("惠商天下")
                .build();
    }
}
