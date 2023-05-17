package com.example.demo.security;

import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@EnableWebSecurity
@Configuration
public class WebSecurity {
    @Autowired
    private CustomAuthenticationEntryPoint unauthorizedHandler;
    private final AuthService authDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManagerBuilder authManagerBuilder;

    public WebSecurity(AuthService authDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManagerBuilder authManagerBuilder) {
        this.authDetailsService = authDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authManagerBuilder = authManagerBuilder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeHttpRequests().requestMatchers(HttpMethod.POST, "/auth").permitAll().requestMatchers(HttpMethod.POST, "/auth/reset").permitAll().and().authorizeHttpRequests().requestMatchers(HttpMethod.POST, "/forgot").permitAll().anyRequest().authenticated().and().addFilter(getAuthenticationFilter()).addFilter(new AuthorizationFilter(authManagerBuilder.getOrBuild())).exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler).and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authenticationProvider(daoAuthenticationProvider());
//        http.cors().and().csrf().disable().authorizeHttpRequests().anyRequest().permitAll();
        return http.build();
    }

    public AuthenticationFilter getAuthenticationFilter() throws Exception {
        final AuthenticationFilter filter = new AuthenticationFilter(authManagerBuilder.getOrBuild());
        filter.setFilterProcessesUrl("/auth/login");
        return filter;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(authDetailsService);
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        return provider;
    }
}
