package com.INTStore.EcommerceSiteWithSpring.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable());
        http.authorizeHttpRequests((request) -> request
                .requestMatchers("/api/product/**")
                        .hasAuthority("ADMIN")
                        .requestMatchers("/api/product/**")
                        .hasAuthority("USER")
                .requestMatchers("/api/seller/**")
                        .permitAll()
                .requestMatchers("/api/customer/**")
                        .permitAll()
                        .requestMatchers("/api/admin/**")
                        .permitAll()
                .anyRequest().authenticated())


                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
