package com.pidSpringBoot.pidSpringBoot.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@Order(1)
public class WebSecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private LoginSuccessHandler successHadeler;

    @Autowired
    private LogoutSuccessHandeler logoutSuccessHandeler;

    @Bean
    public CustomUserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }


    /**
    @Bean
    protected void configure(AuthenticationManagerBuilder authenticationManageBuilder) throws Exception{
        authenticationManageBuilder.authenticationProvider(authenticationProvider());
    }**/


    @Bean
    public SecurityFilterChain securityFilterChainConfig(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authenticationProvider(authenticationProvider());
        httpSecurity.authorizeRequests().
                requestMatchers("/","/signup").permitAll()
                //.requestMatchers("/locations").hasAuthority("ADMIN")
                .requestMatchers("/admin/**").hasAuthority("Admin")
                .requestMatchers("/member/**").hasAnyAuthority("Member","Admin")
                .and()
                .formLogin()
                    .usernameParameter("login")
                    .successHandler(successHadeler)
                    .permitAll()
                .and()
                .logout()
                    //.logoutSuccessUrl("/")
                    .logoutSuccessHandler(logoutSuccessHandeler)
                    .permitAll();
        return httpSecurity.build();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/images/**","/js/**","/webjars/**");
    }

}
