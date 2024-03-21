package com.easyapp.adm.mathias.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class FirebaseSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> {
                    //frontEnd
                    req.requestMatchers(
                            "/",
                                     "/app/**"
                    ).permitAll();
                    //backEnd
                    req.requestMatchers(
                            "/auth/token/**",
                                     "/auth/reset-password/**"
                    ).permitAll();
                    req.anyRequest().authenticated();
                })
                .addFilterBefore(new FirebaseFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
