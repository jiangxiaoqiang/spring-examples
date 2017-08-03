package com.hualongdata.springstarter.web.config;

import com.fasterxml.classmate.TypeResolver;
import com.hualongdata.springstarter.web.setting.SwaggerSetting;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

/**
 * Created by yangbajing(yangbajing@gmail.com) on 2017-07-25.
 */
@Configuration
@EnableSwagger2
//@ComponentScan({"com.hualongdata.springstarter.web.controller"})
@AutoConfigureAfter(WebConfiguration.class)
public class SwaggerConfiguration {
    private final TypeResolver typeResolver;
    private final SwaggerSetting swaggerSetting;

    public SwaggerConfiguration(TypeResolver typeResolver, SwaggerSetting swaggerSetting) {
        this.typeResolver = typeResolver;
        this.swaggerSetting = swaggerSetting;
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerSetting.isShow())
                .groupName(swaggerSetting.getGroup())
                .apiInfo(new ApiInfo(
                        swaggerSetting.getTitle(),
                        swaggerSetting.getDescription(),
                        swaggerSetting.getVersion(),
                        swaggerSetting.getTermsOfServiceUrl(),
                        new Contact(swaggerSetting.getContactName(), swaggerSetting.getContactUrl(), swaggerSetting.getContactEmail()),
                        swaggerSetting.getLicense(),
                        swaggerSetting.getLicenseUrl(), new ArrayList<>()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hualongdata.springstarter.web.controller"))
                .paths(PathSelectors.any())
////                .paths(PathSelectors.ant("/api"))
                .build()
                .directModelSubstitute(ZonedDateTime.class, String.class)
                .directModelSubstitute(LocalDateTime.class, String.class)
                .directModelSubstitute(LocalDate.class, String.class)
                .directModelSubstitute(LocalTime.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .pathMapping("/")
                .alternateTypeRules(
                        newRule(typeResolver.resolve(DeferredResult.class,
                                typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                                typeResolver.resolve(WildcardType.class)))
                .enableUrlTemplating(true);
    }

}
