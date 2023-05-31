package com.legot.config.security;

import com.legot.jwt.JwtFilter;
import com.legot.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {
    private final JwtProvider jwtProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .httpBasic().disable()
                .csrf().disable()
                .cors()
                .and()
                .formLogin().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users/signin").permitAll()
                .antMatchers(HttpMethod.POST, "/users/signup").permitAll()
                .anyRequest().hasAnyAuthority("USER")
                .and()
                .addFilterBefore(new JwtFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));

        return http.build();
    }
}
