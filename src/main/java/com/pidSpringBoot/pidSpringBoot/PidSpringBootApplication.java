package com.pidSpringBoot.pidSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
public class PidSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(PidSpringBootApplication.class, args);
	}
	@Bean
	public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
   	 return new HiddenHttpMethodFilter();
	}
	

}
