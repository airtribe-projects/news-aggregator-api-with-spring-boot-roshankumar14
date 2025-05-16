package com.newsAggregator.newsAggregator.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;


@Configuration
public class AuthConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, HandlerMappingIntrospector introspector) throws Exception {

        MvcRequestMatcher matcher = new MvcRequestMatcher(introspector, "/v1/news/verifyToken");
        matcher.setMethod(HttpMethod.POST);


        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorizeHttpRequests ->authorizeHttpRequests
                                .requestMatchers("/h2-console/**", "/v1/news/register/**" , "/v1/news/verifyToken","/v1/news/login","/v1/news/preference", "/news")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.ignoringRequestMatchers("/h2-console/**", "/v1/news/register/**", "/v1/news/verifyToken","/v1/news/login","/v1/news/preference","/news"))
                .headers(AbstractHttpConfigurer::disable)
                .formLogin(formLogin -> formLogin.defaultSuccessUrl("/login", true).permitAll())
                . addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);;

        return httpSecurity.build();
    }
}



