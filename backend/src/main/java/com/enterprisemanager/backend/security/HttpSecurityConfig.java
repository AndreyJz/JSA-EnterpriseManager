package com.enterprisemanager.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.enterprisemanager.backend.security.filter.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfig {
    @Autowired
    private AuthenticationProvider daoAuthProvider;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        SecurityFilterChain filterChain = http
                .csrf( csrfConfig -> csrfConfig.disable() )
                .sessionManagement( sessMagConfig -> sessMagConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS) )
                .authenticationProvider(daoAuthProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests( authReqConfig -> {
                    buildRequestMatchers(authReqConfig);
                } )
                .build();

        return filterChain;
    }

    private static void buildRequestMatchers(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authReqConfig) {
        // authReqConfig.requestMatchers(HttpMethod.POST, "/customers").permitAll();
        // authReqConfig.requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll();
        // authReqConfig.requestMatchers(HttpMethod.GET, "/auth/validate-token").permitAll();
        // authReqConfig.requestMatchers(HttpMethod.GET, "/api/person/**").permitAll();
        // authReqConfig.requestMatchers(HttpMethod.GET, "/api/service").permitAll();
        // authReqConfig.requestMatchers(HttpMethod.GET, "/api/country").permitAll();
        // authReqConfig.requestMatchers(HttpMethod.GET, "/api/region").permitAll();
        // authReqConfig.requestMatchers(HttpMethod.GET, "/api/city").permitAll();
        // authReqConfig.requestMatchers(HttpMethod.GET, "/api/branch").permitAll();
        // authReqConfig.requestMatchers(HttpMethod.GET, "/api/company").permitAll();
        // authReqConfig.requestMatchers(HttpMethod.GET, "/api/serviceApproval").permitAll();
        authReqConfig.anyRequest().permitAll();

        // authReqConfig.anyRequest().authenticated();
    }
}
