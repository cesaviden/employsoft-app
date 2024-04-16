package com.app.employsoft.auth.config;

import com.app.employsoft.auth.config.filter.JwtTokenValidator;
import com.app.employsoft.auth.services.UserDetailServiceImpl;
import com.app.employsoft.auth.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtUtils jwtUtils;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    // Configurar los endpoints publicos
                    http.requestMatchers(HttpMethod.POST, "/auth/**").permitAll();

                    // Configurar el resto de endpoint - ESPECIFICADOS
                    http.requestMatchers(HttpMethod.GET, "/api/v1/tasks/**").hasAuthority("READ");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/tasks/**").hasRole("SUPERVISOR");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/tasks/**").hasAnyAuthority("UPDATE");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/tasks/**").hasRole("SUPERVISOR");

                    http.requestMatchers(HttpMethod.GET, "/api/v1/projects/**").hasAuthority("READ");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/projects/**").hasRole("SUPERVISOR");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/projects/**").hasAnyAuthority("UPDATE");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/projects/**").hasRole("SUPERVISOR");

                    http.requestMatchers(HttpMethod.GET, "/api/v1/messages/**").hasAuthority("READ");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/messages/**").hasAnyRole("SUPERVISOR", "EMPLOYEE");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/messages/**").hasAnyAuthority("SUPERVISOR", "EMPLOYEE");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/messages/**").hasAnyRole("SUPERVISOR", "EMPLOYEE");
                    
                    // Configurar el resto de endpoint - NO ESPECIFICADOS
                    http.anyRequest().denyAll();
                })
                .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
