package com.projects.LibraryManagementSystem.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Value("${student.authority}")
    private String studentAuthority;

    @Value("${admin.authority}")
    private String adminAuthority;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/addStudent/**").permitAll()
                        .requestMatchers("/user/addAdmin/**").permitAll()
                        .requestMatchers("/user/filter/**").hasAnyAuthority(studentAuthority, adminAuthority)
                        .requestMatchers("/txn/issue/**").hasAuthority(adminAuthority)
                        .requestMatchers("/txn/return/**").hasAuthority(adminAuthority)
                        .requestMatchers("/book/addBook/**").hasAuthority(adminAuthority)
                        .requestMatchers("/book/filter/**").hasAnyAuthority(studentAuthority, adminAuthority)
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
