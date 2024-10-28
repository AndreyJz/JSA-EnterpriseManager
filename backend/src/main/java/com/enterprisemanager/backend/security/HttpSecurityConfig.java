package com.enterprisemanager.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;

import com.enterprisemanager.backend.security.filter.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity

public class HttpSecurityConfig {
    @Autowired
    private AuthenticationProvider daoAuthProvider;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private AuthorizationManager<RequestAuthorizationContext> authorizationManager;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173")); // Cambia al origen de tu frontend
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        SecurityFilterChain filterChain = http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrfConfig -> csrfConfig.disable())
                .sessionManagement(
                        sessMagConfig -> sessMagConfig
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(daoAuthProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests( authReqConfig -> {
//                   authReqConfig.anyRequest().permitAll();
                     authReqConfig.anyRequest().access(authorizationManager);
                } )
                .exceptionHandling( exceptionConfig -> {
                    exceptionConfig.authenticationEntryPoint(authenticationEntryPoint);
                    exceptionConfig.accessDeniedHandler(accessDeniedHandler);
                })
                .build();

        return filterChain;
    }

//    private static void buildRequestMatchers(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authReqConfig) {
//        authReqConfig.requestMatchers(HttpMethod.POST, "/customers").permitAll();
//        authReqConfig.requestMatchers(HttpMethod.POST, "/auth/login").permitAll();
//        authReqConfig.requestMatchers(HttpMethod.GET, "/auth/validate-token").permitAll();
//
//        authReqConfig.requestMatchers(HttpMethod.POST, "/country").hasAuthority("CREATE_ONE_COUNTRY");
//        authReqConfig.requestMatchers(HttpMethod.PUT, "/country/**").hasAuthority("UPDATE_ONE_COUNTRY");
//        authReqConfig.requestMatchers(HttpMethod.DELETE, "/country/**").hasAuthority("DISABLE_ONE_COUNTRY");
//
//        authReqConfig.requestMatchers(HttpMethod.POST, "/region").hasAuthority("CREATE_ONE_REGION");
//        authReqConfig.requestMatchers(HttpMethod.PUT, "/region/**").hasAuthority("UPDATE_ONE_REGION");
//        authReqConfig.requestMatchers(HttpMethod.DELETE, "/region/**").hasAuthority("DISABLE_ONE_REGION");
//
//        authReqConfig.requestMatchers(HttpMethod.POST, "/city").hasAuthority("CREATE_ONE_CITY");
//        authReqConfig.requestMatchers(HttpMethod.PUT, "/city/**").hasAuthority("UPDATE_ONE_CITY");
//        authReqConfig.requestMatchers(HttpMethod.DELETE, "/city/**").hasAuthority("DISABLE_ONE_CITY");
//
//        authReqConfig.requestMatchers(HttpMethod.POST, "/supply").hasAuthority("CREATE_ONE_SUPPLY");
//        authReqConfig.requestMatchers(HttpMethod.PUT, "/supply/**").hasAuthority("UPDATE_ONE_SUPPLY");
//        authReqConfig.requestMatchers(HttpMethod.DELETE, "/supply/**").hasAuthority("DISABLE_ONE_SUPPLY");
//
//        authReqConfig.requestMatchers(HttpMethod.POST, "/supplyService").hasAuthority("CREATE_ONE_SUPPLY_SERVICE");
//        authReqConfig.requestMatchers(HttpMethod.PUT, "/supplyService/**").hasAuthority("UPDATE_ONE_SUPPLY_SERVICE");
//        authReqConfig.requestMatchers(HttpMethod.DELETE, "/supplyService/**").hasAuthority("DISABLE_ONE_SUPPLY_SERVICE");
//
//        authReqConfig.requestMatchers(HttpMethod.POST, "/service").hasAuthority("CREATE_ONE_SERVICE");
//        authReqConfig.requestMatchers(HttpMethod.PUT, "/service/**").hasAuthority("UPDATE_ONE_SERVICE");
//        authReqConfig.requestMatchers(HttpMethod.DELETE, "/service/**").hasAuthority("DISABLE_ONE_SERVICE");
//
//        authReqConfig.requestMatchers(HttpMethod.POST, "/serviceBranch").hasAuthority("CREATE_ONE_SERVICE_BRANCH");
//        authReqConfig.requestMatchers(HttpMethod.PUT, "/serviceBranch/**").hasAuthority("UPDATE_ONE_SERVICE_BRANCH");
//        authReqConfig.requestMatchers(HttpMethod.DELETE, "/serviceBranch/**").hasAuthority("DISABLE_ONE_SERVICE_BRANCH");
//
//        authReqConfig.requestMatchers(HttpMethod.POST, "/serviceApproval").hasAuthority("CREATE_ONE_SERVICE_APPROVAL");
//        authReqConfig.requestMatchers(HttpMethod.PUT, "/serviceApproval/**").hasAuthority("UPDATE_ONE_SERVICE_APPROVAL");
//        authReqConfig.requestMatchers(HttpMethod.DELETE, "/serviceApproval/**").hasAuthority("DISABLE_ONE_SERVICE_APPROVAL");
//
//        authReqConfig.requestMatchers(HttpMethod.POST, "/branch").hasAuthority("CREATE_ONE_BRANCH");
//        authReqConfig.requestMatchers(HttpMethod.PUT, "/branch/**").hasAuthority("UPDATE_ONE_BRANCH");
//        authReqConfig.requestMatchers(HttpMethod.DELETE, "/branch/**").hasAuthority("DISABLE_ONE_BRANCH");
//
//        authReqConfig.requestMatchers(HttpMethod.POST, "/company").hasAuthority("CREATE_ONE_COMPANY");
//        authReqConfig.requestMatchers(HttpMethod.PUT, "/company/**").hasAuthority("UPDATE_ONE_COMPANY");
//        authReqConfig.requestMatchers(HttpMethod.DELETE, "/company/**").hasAuthority("DISABLE_ONE_COMPANY");
//
//        authReqConfig.requestMatchers(HttpMethod.GET, "/companyType").hasAuthority("READ_ALL_COMPANY_TYPES");
//        authReqConfig.requestMatchers(HttpMethod.GET, "/companyType/**").hasAuthority("READ_ONE_COMPANY_TYPE");
//        authReqConfig.requestMatchers(HttpMethod.POST, "/companyType").hasAuthority("CREATE_ONE_COMPANY_TYPE");
//        authReqConfig.requestMatchers(HttpMethod.PUT, "/companyType/**").hasAuthority("UPDATE_ONE_COMPANY_TYPE");
//        authReqConfig.requestMatchers(HttpMethod.DELETE, "/companyType/**").hasAuthority("DISABLE_ONE_COMPANY_TYPE");
//
//        authReqConfig.requestMatchers(HttpMethod.GET, "/email").hasAuthority("READ_ALL_EMAILS");
//        authReqConfig.requestMatchers(HttpMethod.GET, "/email/**").hasAuthority("READ_ONE_EMAIL");
////        authReqConfig.requestMatchers(HttpMethod.POST, "/email").hasAuthority("CREATE_ONE_EMAIL");
////        authReqConfig.requestMatchers(HttpMethod.PUT, "/email/**").hasAuthority("UPDATE_ONE_EMAIL");
////        authReqConfig.requestMatchers(HttpMethod.DELETE, "/email/**").hasAuthority("DISABLE_ONE_EMAIL");
//
//        authReqConfig.requestMatchers(HttpMethod.POST, "/emailType").hasAuthority("CREATE_ONE_EMAIL_TYPE");
//        authReqConfig.requestMatchers(HttpMethod.PUT, "/emailType/**").hasAuthority("UPDATE_ONE_EMAIL_TYPE");
//        authReqConfig.requestMatchers(HttpMethod.DELETE, "/emailType/**").hasAuthority("DISABLE_ONE_EMAIL_TYPE");
//
//        authReqConfig.requestMatchers(HttpMethod.GET, "/phone").hasAuthority("READ_ALL_PHONES");
//        authReqConfig.requestMatchers(HttpMethod.GET, "/phone/**").hasAuthority("READ_ONE_PHONE");
////        authReqConfig.requestMatchers(HttpMethod.POST, "/phone").hasAuthority("CREATE_ONE_PHONE");
////        authReqConfig.requestMatchers(HttpMethod.PUT, "/phone/**").hasAuthority("UPDATE_ONE_PHONE");
////        authReqConfig.requestMatchers(HttpMethod.DELETE, "/phone/**").hasAuthority("DISABLE_ONE_PHONE");
//
//        authReqConfig.requestMatchers(HttpMethod.POST, "/phoneType").hasAuthority("CREATE_ONE_PHONE_TYPE");
//        authReqConfig.requestMatchers(HttpMethod.PUT, "/phoneType/**").hasAuthority("UPDATE_ONE_PHONE_TYPE");
//        authReqConfig.requestMatchers(HttpMethod.DELETE, "/phoneType/**").hasAuthority("DISABLE_ONE_PHONE_TYPE");
//
//        authReqConfig.requestMatchers(HttpMethod.GET, "/orderDetail").hasAuthority("READ_ALL_ORDERS");
//        authReqConfig.requestMatchers(HttpMethod.GET, "/orderDetail/**").hasAuthority("READ_ONE_ORDER");
//        authReqConfig.requestMatchers(HttpMethod.POST, "/orderDetail").hasAuthority("CREATE_ONE_ORDER");
//        authReqConfig.requestMatchers(HttpMethod.PUT, "/orderDetail/**").hasAuthority("UPDATE_ONE_ORDER");
//        authReqConfig.requestMatchers(HttpMethod.DELETE, "/orderDetail/**").hasAuthority("DISABLE_ONE_ORDER");
//
//        authReqConfig.requestMatchers(HttpMethod.GET, "/serviceOrder").hasAuthority("READ_ALL_SERVICE_ORDERS");
//        authReqConfig.requestMatchers(HttpMethod.GET, "/serviceOrder/**").hasAuthority("READ_ONE_SERVICE_ORDER");
//        authReqConfig.requestMatchers(HttpMethod.POST, "/serviceOrder").hasAuthority("CREATE_ONE_SERVICE_ORDER");
//        authReqConfig.requestMatchers(HttpMethod.PUT, "/serviceOrder/**").hasAuthority("UPDATE_ONE_SERVICE_ORDER");
//        authReqConfig.requestMatchers(HttpMethod.DELETE, "/serviceOrder/**").hasAuthority("DISABLE_ONE_SERVICE_ORDER");
//
//        authReqConfig.requestMatchers(HttpMethod.GET, "/workOrder").hasAuthority("READ_ALL_WORK_ORDERS");
//        authReqConfig.requestMatchers(HttpMethod.GET, "/workOrder/**").hasAuthority("READ_ONE_WORK_ORDER");
//        authReqConfig.requestMatchers(HttpMethod.POST, "/workOrder").hasAuthority("CREATE_ONE_WORK_ORDER");
//        authReqConfig.requestMatchers(HttpMethod.PUT, "/workOrder/**").hasAuthority("UPDATE_ONE_WORK_ORDER");
//        authReqConfig.requestMatchers(HttpMethod.DELETE, "/workOrder/**").hasAuthority("DISABLE_ONE_WORK_ORDER");
//
//        authReqConfig.requestMatchers(HttpMethod.GET, "/workOrderDetail").hasAuthority("READ_ALL_WORK_ORDER_DETAILS");
//        authReqConfig.requestMatchers(HttpMethod.GET, "/workOrderDetail/**").hasAuthority("READ_ONE_WORK_ORDER_DETAIL");
//        authReqConfig.requestMatchers(HttpMethod.POST, "/workOrderDetail").hasAuthority("CREATE_ONE_WORK_ORDER_DETAIL");
//        authReqConfig.requestMatchers(HttpMethod.PUT, "/workOrderDetail/**").hasAuthority("UPDATE_ONE_WORK_ORDER_DETAIL");
//        authReqConfig.requestMatchers(HttpMethod.DELETE, "/workOrderDetail/**").hasAuthority("DISABLE_ONE_WORK_ORDER_DETAIL");
//
//        authReqConfig.requestMatchers(HttpMethod.GET, "/workOrderDetailStatus").hasAuthority("READ_ALL_WORK_ORDER_DETAILS_STATUSES");
//        authReqConfig.requestMatchers(HttpMethod.GET, "/workOrderDetailStatus/**").hasAuthority("READ_ONE_WORK_ORDER_DETAILS_STATUS");
//        authReqConfig.requestMatchers(HttpMethod.POST, "/workOrderDetailStatus").hasAuthority("CREATE_ONE_WORK_ORDER_DETAILS_STATUS");
//        authReqConfig.requestMatchers(HttpMethod.PUT, "/workOrderDetailStatus/**").hasAuthority("UPDATE_ONE_WORK_ORDER_DETAILS_STATUS");
//        authReqConfig.requestMatchers(HttpMethod.DELETE, "/workOrderDetailStatus/**").hasAuthority("DISABLE_ONE_WORK_ORDER_DETAILS_STATUS");
//
//        authReqConfig.requestMatchers(HttpMethod.GET, "/workOrderStatus").hasAuthority("READ_ALL_WORK_ORDER_STATUSES");
//        authReqConfig.requestMatchers(HttpMethod.GET, "/workOrderStatus/**").hasAuthority("READ_ONE_WORK_ORDER_STATUS");
//        authReqConfig.requestMatchers(HttpMethod.POST, "/workOrderStatus").hasAuthority("CREATE_ONE_WORK_ORDER_STATUS");
//        authReqConfig.requestMatchers(HttpMethod.PUT, "/workOrderStatus/**").hasAuthority("UPDATE_ONE_WORK_ORDER_STATUS");
//        authReqConfig.requestMatchers(HttpMethod.DELETE, "/workOrderStatus/**").hasAuthority("DISABLE_ONE_WORK_ORDER_STATUS");
//
//        authReqConfig.requestMatchers(HttpMethod.GET, "/approvalStatus").hasAuthority("READ_ALL_APPROVAL_STATUSES");
//        authReqConfig.requestMatchers(HttpMethod.GET, "/approvalStatus/**").hasAuthority("READ_ONE_APPROVAL_STATUS");
//        authReqConfig.requestMatchers(HttpMethod.POST, "/approvalStatus").hasAuthority("CREATE_ONE_APPROVAL_STATUS");
//        authReqConfig.requestMatchers(HttpMethod.PUT, "/approvalStatus/**").hasAuthority("UPDATE_ONE_APPROVAL_STATUS");
//        authReqConfig.requestMatchers(HttpMethod.DELETE, "/approvalStatus/**").hasAuthority("DISABLE_ONE_APPROVAL_STATUS");
//
//        authReqConfig.requestMatchers(HttpMethod.GET, "/person").hasAuthority("READ_ALL_PEOPLE");
//        authReqConfig.requestMatchers(HttpMethod.GET, "/person/**").hasAuthority("READ_ONE_PERSON");
////        authReqConfig.requestMatchers(HttpMethod.POST, "/person").hasAuthority("CREATE_ONE_PERSON");
////        authReqConfig.requestMatchers(HttpMethod.PUT, "/person/**").hasAuthority("UPDATE_ONE_PERSON");
//        authReqConfig.requestMatchers(HttpMethod.DELETE, "/person/**").hasAuthority("DISABLE_ONE_PERSON");
//
//        authReqConfig.requestMatchers(HttpMethod.GET, "/personSupply").hasAuthority("READ_ALL_PERSON_SUPPLIES");
//        authReqConfig.requestMatchers(HttpMethod.GET, "/personSupply/**").hasAuthority("READ_ONE_PERSON_SUPPLY");
//        authReqConfig.requestMatchers(HttpMethod.POST, "/personSupply").hasAuthority("CREATE_ONE_PERSON_SUPPLY");
//        authReqConfig.requestMatchers(HttpMethod.PUT, "/personSupply/**").hasAuthority("UPDATE_ONE_PERSON_SUPPLY");
//        authReqConfig.requestMatchers(HttpMethod.DELETE, "/personSupply/**").hasAuthority("DISABLE_ONE_PERSON_SUPPLY");
//
//        authReqConfig.requestMatchers(HttpMethod.POST, "/personType").hasAuthority("CREATE_ONE_PERSON_TYPE");
//        authReqConfig.requestMatchers(HttpMethod.PUT, "/personType/**").hasAuthority("UPDATE_ONE_PERSON_TYPE");
//        authReqConfig.requestMatchers(HttpMethod.DELETE, "/personType/**").hasAuthority("DISABLE_ONE_PERSON_TYPE");
//
//        authReqConfig.anyRequest().permitAll();
//    }
}
