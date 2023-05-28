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
        registry.addViewController("/admin/home").setViewName("admin/admin_home");
        registry.addViewController("/profile/edit").setViewName("user/create");
        registry.addViewController("/profile/edit/{id}").setViewName("user/edit");
        registry.addViewController("/admin/edit/{id}").setViewName("admin/admin_edit_user");
        registry.addViewController("/member/home").setViewName("user/index");
        registry.addViewController("/admin/list_users").setViewName("admin/list_users");
        registry.addViewController("/signup").setViewName("user/signup_success");
        registry.addViewController("/process_signup").setViewName("user/user_signup_form");
        registry.addViewController("/users/{id}").setViewName("user/show");
        registry.addViewController("/shows").setViewName("show/index");
        registry.addViewController("/shows/{id}").setViewName("show/show");
        registry.addViewController("/shows/create").setViewName("show/create");
        registry.addViewController("/shows/edit/{id}").setViewName("show/edit");
        registry.addViewController("/cart").setViewName("cart");


        
        
        
    }
    
    @Bean
	public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
   	 return new HiddenHttpMethodFilter();
	}
	
}
