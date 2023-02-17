package com.a406.mrm.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Component
public class SwaggerConfiguration {

//	Swagger-UI 2.x 확인
//	http://localhost[:8080]/{your-app-root}/swagger-ui.html
//	Swagger-UI 3.x 확인
//	http://localhost[:8080]/{your-app-root}/swagger-ui/index.html

    private String version = "V1";
    private String title = "MRM" + version;

//    @Bean
//    public Docket api() {
//        ParameterBuilder ATParameterBuilder = new ParameterBuilder();
//        ATParameterBuilder.name("access-token") //헤더 이름
//                .description("Access Tocken") //설명
//                .modelRef(new ModelRef("string"))
//                .parameterType("header")
//                .required(false)
//                .build();
//        ParameterBuilder RTParameterBuilder = new ParameterBuilder();
//        RTParameterBuilder.name("refresh-token") //헤더 이름
//                .description("Refresh Tocken") //설명
//                .modelRef(new ModelRef("string"))
//                .parameterType("header")
//                .required(false)
//                .build();
//        List<Parameter> tokenParameters = new ArrayList<>();
//        tokenParameters.add(ATParameterBuilder.build());
//        tokenParameters.add(RTParameterBuilder.build());
//
//        return new Docket(DocumentationType.SWAGGER_2).consumes(getConsumeContentTypes()).produces(getProduceContentTypes())
//                .apiInfo(apiInfo()).groupName(version).select()
//                .apis(RequestHandlerSelectors.basePackage("com.a406.mrm"))
//                .paths(regex("/.*")).build()
//                .useDefaultResponseMessages(false)
//                .globalOperationParameters(tokenParameters);
//    }

    private Set<String> getConsumeContentTypes() {
        Set<String> consumes = new HashSet<>();
        consumes.add("application/json;charset=UTF-8");
//      consumes.add("application/xml;charset=UTF-8");
        consumes.add("application/x-www-form-urlencoded");
        return consumes;
    }

    private Set<String> getProduceContentTypes() {
        Set<String> produces = new HashSet<>();
        produces.add("application/json;charset=UTF-8");
        return produces;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(title)
                .description("<h3>mrm<br>")
                .contact(new Contact("mrm", "", ""))
                .license("MRM License")
                .licenseUrl("")
                .version("1.0").build();
    }

}