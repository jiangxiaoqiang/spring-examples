package com.hualongdata.springstarter.web.setting;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by yangbajing(yangbajing@gmail.com) on 2017-07-25.
 */
@Component
@ConfigurationProperties(prefix = "springfox.api")
@Data
public class SwaggerSetting {
    private boolean show;
    private String group;
    private String title;
    private String description;
    private String version;
    private String termsOfServiceUrl;
    private String contactName;
    private String contactUrl;
    private String contactEmail;
    private String license;
    private String licenseUrl;
}
