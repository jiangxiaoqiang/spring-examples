package com.hualongdata.springstarter.data.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Data: JPA
 * Created by Yang Jing (yangbajing@gmail.com) on 2017-07-24.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.hualongdata.springstarter.data.repository")
@EntityScan(basePackages = "com.hualongdata.springstarter.data.entity")
@EnableJpaAuditing
public class DataConfiguration {
}
