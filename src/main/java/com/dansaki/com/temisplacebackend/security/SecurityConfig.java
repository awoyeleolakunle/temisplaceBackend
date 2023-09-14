package com.dansaki.com.temisplacebackend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfig {

private final AuthenticationProvider authenticationProvider;
private final JwtAuthenticationFilter jwtAuthenticationFilter;

private final String[] allowedEndPoints ={"/api/v1/temisplace/blog/blogPostCreation",
        "/api/v1/temisplace/Orders/makeOrder", "/api/v1/temisplace/register",
        "/api/v1/temisplace/Orders/orderCompletion", "/api/v1/temisplace/Orders/orderCancellation",
        "/api/v1/temisplace/dashBoardInfo", "/api/v1/temisplace/allBlogPost",
        "/api/v1/temisplace/paginatedUserList", "/api/v1/temisplace/itemCreation"};

@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.
            csrf(AbstractHttpConfigurer::disable)
//            .headers(AbstractHttpConfigurer::disable)
            .headers(httpSecurityHeadersConfigurer ->
                    httpSecurityHeadersConfigurer.addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Origin", "http://localhost:3000")))
            .authorizeHttpRequests((auth -> {
               auth.requestMatchers(allowedEndPoints).permitAll();}))
            .sessionManagement((session) -> {
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            })
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
}

}


