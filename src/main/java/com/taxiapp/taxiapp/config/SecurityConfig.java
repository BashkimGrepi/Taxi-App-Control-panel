package com.taxiapp.taxiapp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class SecurityConfig {


     
        @Bean
        public SecurityFilterChain configure(HttpSecurity http
        ) throws Exception {
                http.authorizeHttpRequests(auth -> auth
                                .requestMatchers("/", "/login", "/home", "/guest/**").permitAll()
                                .requestMatchers("/h2-console/**").permitAll()
                                .requestMatchers("/api/**").permitAll()
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/driver/**").hasRole("DRIVER")
                                .requestMatchers("/user/**").hasRole("USER")
                                

                                .anyRequest().authenticated())

                                .formLogin(login -> login
                                                .loginPage("/login")
                                                .defaultSuccessUrl("/home", true)
                                                .permitAll())
                                .exceptionHandling(
                                                ex -> ex
                                                .defaultAuthenticationEntryPointFor(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED), new AntPathRequestMatcher("/api/**")))

                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessUrl("/login?logout")
                                                .permitAll());

                //for jwt authentication we need to disable the default session management
                //and use stateless session management

                return http.build();
        }
        
        @Bean
        public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
                UserDetails user = org.springframework.security.core.userdetails.User
                                .withUsername("user")
                                .password(passwordEncoder.encode("user"))
                                .roles("USER")
                                .build();
                UserDetails admin = org.springframework.security.core.userdetails.User
                                .withUsername("admin")
                                .password(passwordEncoder.encode("admin"))
                                .roles("ADMIN")
                                .build();
                UserDetails driver = org.springframework.security.core.userdetails.User
                                .withUsername("driver")
                                .password(passwordEncoder.encode("driver"))
                                .roles("DRIVER")
                                .build();

                        return new InMemoryUserDetailsManager(user, admin, driver);
        }
        
                @Bean
                public PasswordEncoder passwordEncoder() {
                        return new BCryptPasswordEncoder();
                }

        


 
}
    
    

    