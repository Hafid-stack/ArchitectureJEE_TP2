package com.enset.architecturejee_tp2.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
        @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            return new InMemoryUserDetailsManager(
                    User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("USER").build(),
                     User.withUsername("user2").password(passwordEncoder.encode("1234")).roles("USER").build(),
                      User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build()
            );
        }

        @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            return http
                    .formLogin(fr-> fr.loginPage("/login").permitAll())
                    .csrf(Customizer.withDefaults())
                    .authorizeHttpRequests(ar->ar.requestMatchers("/admin/**").hasRole("ADMIN"))
                    .authorizeHttpRequests(ar-> ar.requestMatchers("/user/**").hasRole("USER"))
                    .authorizeHttpRequests(ar->ar.requestMatchers("/public/**","/webjars/**").permitAll())
                    .authorizeHttpRequests(ar -> ar.anyRequest().authenticated())
                    .exceptionHandling(er-> er.accessDeniedPage("/notAuthorized"))
                    .build();
        }
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
}
