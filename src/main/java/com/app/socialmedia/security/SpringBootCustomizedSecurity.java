package com.app.socialmedia.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringBootCustomizedSecurity {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        //1) all request should be authenticated
        httpSecurity.authorizeHttpRequests( auth->auth.anyRequest().authenticated());
        //2) if not authenticated provide a popup to request credentials
        httpSecurity.httpBasic(Customizer.withDefaults());
        //3) disable csrf
        httpSecurity.csrf().disable();
        return httpSecurity.build();
    }

}
