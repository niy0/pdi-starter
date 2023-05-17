package com.pidSpringBoot.pidSpringBoot;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin/home").setViewName("admin_home");
        registry.addViewController("/user/member_home").setViewName("member_home");
    }
    
    @Bean
	public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
   	 return new HiddenHttpMethodFilter();
	}
	
}
