package com.oekrem.SpringMVCBackEnd.config;

import com.oekrem.SpringMVCBackEnd.repository.UserRepository;
import com.oekrem.SpringMVCBackEnd.security.JwtAuthenticationFilter;
import com.oekrem.SpringMVCBackEnd.security.OrderUserDetailsService;
import com.oekrem.SpringMVCBackEnd.services.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthenticationService authService) {
        return new JwtAuthenticationFilter(authService);
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        OrderUserDetailsService userDetailsService = new OrderUserDetailsService(userRepository);
        return userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests( auth -> auth
                        // Security Options
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/refresh").authenticated()
                        // Application Options
                        .requestMatchers(HttpMethod.GET, "/api/v1/categories/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/products/**").permitAll()

                        // aşağısı hep authenticated() olacak ama şimdilik devamke
                        .requestMatchers(HttpMethod.GET, "/api/v1/addresses/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/payments/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/orders/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/orderdetails/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/v1/addresses/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/payments/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/orders/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/orderdetails/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/**").permitAll()

                        .requestMatchers(HttpMethod.PUT, "/api/v1/addresses/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/payments/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/orders/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/orderdetails/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/users/**").permitAll()

                        .requestMatchers(HttpMethod.PATCH, "/api/v1/addresses/**").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/payments/**").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/orders/**").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/orderdetails/**").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/users/**").permitAll()

                        .anyRequest().authenticated()
                )
                .csrf(csrf-> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) throws Exception {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authProvider);
    }

}
