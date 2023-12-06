package com.dev.alex.phonecollect.config;

import com.dev.alex.phonecollect.service.JpaUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JpaUserDetailsService jpaUserDetailsService;

    public SecurityConfig(JpaUserDetailsService jpaUserDetailsService) {
        this.jpaUserDetailsService = jpaUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests(authorize ->
                        authorize.requestMatchers("/registration/**")
                               .permitAll()
                )
                .formLogin(formLogin -> formLogin.loginPage("/registerPage.ftl").permitAll())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .userDetailsService(jpaUserDetailsService);

        return http.build();
    }
}
