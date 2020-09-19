package com.example.demo.util;


import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.github.xiaoymin.swaggerbootstrapui.model.SpringAddtionalModel;
import com.github.xiaoymin.swaggerbootstrapui.service.SpringAddtionalModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig {

    private static final String SWAGGER_API_VERSION = "2.0";
    private static final String LICENSE_TEXT = "License";
    private static final String title = "WEB REST API";
    private static final String description = "RESTful API for WEB";

    @Autowired
    SpringAddtionalModelService springAddtionalModelService;

    @Bean(value = "defaultApi")
    public Docket productApi( ) {
        SpringAddtionalModel springAddtionalModel= springAddtionalModelService.scan("com.example.demo.entity");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("主接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                .paths(PathSelectors.any())
                .build().additionalModels(springAddtionalModel.getFirst(),springAddtionalModel.getRemaining());
    }
    @Bean(value = "groupApi")
    public Docket groupRestApi() {
        SpringAddtionalModel springAddtionalModel= springAddtionalModelService.scan("com.example.demo.entity");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(groupApiInfo())
                .groupName("分组接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                .paths(PathSelectors.any())
                .build().additionalModels(springAddtionalModel.getFirst(),springAddtionalModel.getRemaining());
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .license(LICENSE_TEXT)
                .version(SWAGGER_API_VERSION)
                .build();
    }
    private ApiInfo groupApiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .license(LICENSE_TEXT)
                .version(SWAGGER_API_VERSION)
                .build();
    }


}
