/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.banburysymphony.orchestra.security;

/**
 * Security configuration for the application
 *
 * @author dave.settle@osinet.co.uk on 24 Aug 2022
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);
    
    /**
     * All requests by default require HTTP BASIC authentication, apart from
     * requests to list things
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/**/list*").permitAll().and()
                .authorizeRequests().antMatchers("/webjars/**").permitAll().and()
                .authorizeRequests().antMatchers("/css/*").permitAll().and()
                .authorizeRequests().antMatchers("/images/**").permitAll().and()
                .authorizeRequests().antMatchers("/user/register").permitAll().and()
                .authorizeRequests().antMatchers("/**").hasRole("ADMIN").and().
                formLogin().permitAll();
        return http.build();
    }
 }
