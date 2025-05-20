package com.security.SpringSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//This annotation marks the SecurityConfig class as a configuration class.
@Configuration
//By writing this you want to mention that do not go into the default flow, just lead how I am directing you.
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    private UserDetailsService userDetailsService;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        return http
            // Disableing the csrf token
            .csrf(customizer -> customizer.disable())
            // No one should be able to enter without authentication
            .authorizeHttpRequests(request -> request.anyRequest().authenticated())
            //To enable form login for the browser
            //disabble this, when you are making a request through browser
            // .formLogin(Customizer.withDefaults())
            //To enable the same for the thunder client or postman
            .httpBasic(Customizer.withDefaults())
            //To make out http request stateless, i.e each request from a client to server is treated as independent transaction
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .build();
    
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){


        //This need to get connected with database
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    
}
