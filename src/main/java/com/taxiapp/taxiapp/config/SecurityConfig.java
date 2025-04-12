package com.taxiapp.taxiapp.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "login").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/driver/**").hasRole("DRIVER")
                .requestMatchers("/user/**").hasRole("USER")
                .requestMatchers("/home").hasAnyRole("ADMIN", "DRIVER", "USER")
                .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/home")
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll());

        return http.build();
    }
    
    

    @Bean
    public UserDetailsService userDetailsService() {
        List<UserDetails> users = new ArrayList<UserDetails>();

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        UserDetails admin = User
                .withUsername("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();
            
        users.add(admin);

        UserDetails driver = User
                .withUsername("driver")
                .password(passwordEncoder.encode("driver"))
                .roles("DRIVER")
                .build();
        users.add(driver);

        UserDetails user = User
                .withUsername("user")
                .password(passwordEncoder.encode("user"))
                .roles("USER")
                .build();
        users.add(user);

        return new InMemoryUserDetailsManager(users);
    }
}
