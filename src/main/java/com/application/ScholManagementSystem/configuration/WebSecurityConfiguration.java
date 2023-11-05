package com.application.ScholManagementSystem.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
       return httpSecurity
               .cors()
               .and()
               .csrf().disable()
               .authorizeRequests()
               .requestMatchers("/authenticate").permitAll()
               .requestMatchers("/register").permitAll()
               .requestMatchers("/hairdressers/**").permitAll()
               .requestMatchers(HttpMethod.GET,"/hairdressers").permitAll()
               .requestMatchers(HttpMethod.GET,"/hairdressers/**").permitAll()
               .requestMatchers("/user/**").permitAll()
               .requestMatchers("/profile/**").permitAll()
               .requestMatchers("/services/**").permitAll()
               .requestMatchers(HttpMethod.GET,"/users").permitAll()
               .requestMatchers(HttpMethod.GET,"/services").permitAll()
               .requestMatchers(HttpMethod.GET,"/services/**").permitAll()
               .requestMatchers(HttpMethod.POST,"/services/**").permitAll()
               .requestMatchers(HttpMethod.PUT,"/services/**").permitAll()
               .requestMatchers(HttpMethod.DELETE,"/services/**").permitAll()
               .requestMatchers("/appointments/**").permitAll()
               .requestMatchers(HttpMethod.PUT,"/reservations/**").permitAll()
               .requestMatchers(HttpMethod.GET,"/reservations").permitAll()
               .requestMatchers(HttpMethod.POST,"/reservations/**").permitAll()
               .requestMatchers(HttpMethod.POST,"/reservations").permitAll()
               .anyRequest().authenticated()
               .and()
               .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .and()
               .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
 return configuration.getAuthenticationManager();
    }
}
