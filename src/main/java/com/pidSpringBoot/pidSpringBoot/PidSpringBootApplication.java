package com.pidSpringBoot.pidSpringBoot;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class PidSpringBootApplication {

	public static void main(String[] args) {

		SpringApplication.run(PidSpringBootApplication.class, args);
	}

	@Configuration
	public class ThymeleafConfiguration {
		@Bean
		public LayoutDialect thymeleafDialect() {
			return new LayoutDialect();
		}
	}
}
