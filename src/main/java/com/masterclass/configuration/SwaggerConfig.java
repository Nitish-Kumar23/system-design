//package com.masterclass.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//
//@EnableWebSecurity
//public class SwaggerConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth -> auth.requestMatchers("/v3/api-docs/**",
//                                "/swagger-ui/**", "/swagger-ui.html")
//                        .permitAll()
//                        .anyRequest()
//                        .authenticated())
//                .formLogin(formLogin -> formLogin.defaultSuccessUrl("/foos"));
//        return http.build();
//    }
//
//}
