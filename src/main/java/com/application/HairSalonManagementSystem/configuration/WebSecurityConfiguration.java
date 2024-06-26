package com.application.HairSalonManagementSystem.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;


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
               .requestMatchers(HttpMethod.POST,"/authenticate").permitAll()
               .requestMatchers("/register").permitAll()
               .requestMatchers("/hairdressers/**").permitAll()
               .requestMatchers(HttpMethod.GET,"/hairdressers").permitAll()
               .requestMatchers(HttpMethod.GET,"/hairdressers/**").permitAll()
               .requestMatchers("/user/**").permitAll()
               .requestMatchers("/profile/**").permitAll()
               .requestMatchers("/services/**").permitAll()
               .requestMatchers(HttpMethod.GET,"/users").permitAll()
               .requestMatchers(HttpMethod.DELETE,"/users/**").permitAll()
               .requestMatchers(HttpMethod.GET,"/services").permitAll()
               .requestMatchers(HttpMethod.GET,"/services/**").permitAll()
               .requestMatchers(HttpMethod.POST,"/services/**").permitAll()
               .requestMatchers(HttpMethod.PUT,"/services/**").permitAll()
               .requestMatchers(HttpMethod.DELETE,"/services/**").permitAll()
               .requestMatchers("/appointments/**").permitAll()
               .requestMatchers(HttpMethod.PUT,"/reservations/**").permitAll()
               .requestMatchers(HttpMethod.GET,"/reservations/**").permitAll()
               .requestMatchers(HttpMethod.GET,"/reservations/user/**").permitAll()
               .requestMatchers(HttpMethod.GET,"/reservations/hairdresser/**").permitAll()
               .requestMatchers(HttpMethod.GET,"/reservations").permitAll()
               .requestMatchers(HttpMethod.POST,"/reservations/**").permitAll()
               .requestMatchers(HttpMethod.POST,"/reservations").permitAll()
               .requestMatchers(HttpMethod.GET,"/reservations/hairdressers/**").permitAll()
               .requestMatchers(HttpMethod.GET,"/reservations/check-availability").permitAll()
               .requestMatchers(HttpMethod.GET,"/reservations/check-availability/**").permitAll()
               .requestMatchers(HttpMethod.POST,"/payment/create-payment-intent").permitAll()
               .requestMatchers(HttpMethod.GET,"/payment/create-payment-intent").permitAll()
               .requestMatchers(HttpMethod.POST,"/payment/process-payment").permitAll()
               .requestMatchers(HttpMethod.GET,"/payment/process-payment").permitAll()
               .requestMatchers(HttpMethod.GET,"/notifications").permitAll()
               .requestMatchers(HttpMethod.POST,"/notifications/interest").permitAll()
               .requestMatchers(HttpMethod.GET,"/notifications/user/**").permitAll()
               .requestMatchers(HttpMethod.PUT,"/notifications/cancel/**").permitAll()
               .requestMatchers(HttpMethod.GET,"/reviews/add").permitAll()
               .requestMatchers(HttpMethod.POST,"/reviews/add").permitAll()
               .requestMatchers(HttpMethod.GET,"/reviews/add").permitAll()
               .requestMatchers(HttpMethod.GET,"/reviews/**").permitAll()
               .requestMatchers(HttpMethod.PUT,"/reviews/edit/**").permitAll()
               .requestMatchers(HttpMethod.DELETE,"/reviews/delete/**").permitAll()
               .requestMatchers(HttpMethod.GET,"/reviews/exists/**").permitAll()
               .requestMatchers(HttpMethod.GET,"/reviews/reservation/**").permitAll()
               .requestMatchers(HttpMethod.GET,"/message").permitAll()
               .requestMatchers(HttpMethod.POST,"/message").permitAll()
           
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

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        firewall.setAllowUrlEncodedPercent(true);
        return firewall;
    }
}
