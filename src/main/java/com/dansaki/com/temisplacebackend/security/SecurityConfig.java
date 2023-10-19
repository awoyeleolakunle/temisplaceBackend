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
        "/api/v1/temisplace/Orders/makeOrder", "/api/v1/temisplace/register", "/api/v1/temisplace/login",
        "/api/v1/temisplace/sendOtp","/api/v1/temisplace/otpConfirmation",
        "/api/v1/temisplace/Orders/orderCompletion", "/api/v1/temisplace/Orders/orderCancellation",
        "/api/v1/temisplace/dashBoardInfo", "/api/v1/temisplace/allBlogPost",
        "/api/v1/temisplace/paginatedUserList", "/api/v1/temisplace/itemCreation",
        "/api/v1/temisplace/itemCreationOrUpdate",
        "/api/v1/temisplace/itemDeletionById",
        "/api/v1/temisplace/updateUser","/api/v1/temisplace/userStatusUpdate", "/api/v1/temisplace/allItems",
        "/api/v1/temisplace/registerOrUpdateUser"
        ,"/api/v1/temisplace/unitDashboardDetails", "/api/v1/temisplace/unitItemAvailabilityRemoval",
        "/api/v1/temisplace/unitItemAvailabilityAddition", "/api/v1/temisplace/availableUnitItemsUnderItemCategory",
        "/api/v1/temisplace/allItemsUnderAnItemCategory", "/api/v1/temisplace/unitOrderDashBoardDetails",
        "/api/v1/temisplace/unitAllDailyOrdersUnderOrderStatus", "/api/v1/temisplace/namesOfAllItemCategory",
        "/api/v1/temisplace/AUnitAllItemsUnderItemCategory", "/api/v1/temisplace/activeOrderStatusAndOrderStatusManagement",
        "/api/v1/temisplace/footerCreationOrUpdate", "/api/v1/temisplace/itemCategoryNameAndImageCreation"};

@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.
            csrf(AbstractHttpConfigurer::disable)
//            .headers(AbstractHttpConfigurer::disable)
            .headers(httpSecurityHeadersConfigurer ->
                    httpSecurityHeadersConfigurer.addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Origin", "http://127.0.0.1:5500")))
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


