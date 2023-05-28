package com.pidSpringBoot.pidSpringBoot.config;


import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;


@Configuration
public class ThymeleafConfiguration {
    @Bean
    public LayoutDialect thymeleafDialect(){
        return new LayoutDialect();
    }

    @Bean
    public SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
    }

  
}
