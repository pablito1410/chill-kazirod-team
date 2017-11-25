package com.chill.table.football.infrastructure.springconfig;

import com.chill.table.football.application.query.user.UserFinder;
import com.chill.table.football.infrastructure.authentication.JwtAuthenticationFilter;
import com.chill.table.football.infrastructure.authentication.JwtLoginFilter;
import com.chill.table.football.infrastructure.authentication.UserAuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserFinder userFinder;
    private final UserAuthService userAuthService;
    private final ObjectMapper objectMapper;

    @Autowired
    public SecurityConfig(final UserFinder userFinder,
                          final UserAuthService userAuthService,
                          final ObjectMapper objectMapper) {
        super();
        this.userFinder = userFinder;
        this.userAuthService = userAuthService;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/*").permitAll()
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .failureUrl("/login?error")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .deleteCookies("remember-me")
                .logoutSuccessUrl("/dupa")
                .permitAll()
                .and()
                .rememberMe()
                .and()
                .addFilterBefore(new JwtLoginFilter("/login", authenticationManager(), objectMapper),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtAuthenticationFilter(userFinder),
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAuthService).passwordEncoder(new BCryptPasswordEncoder());
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password("password")
//                .roles("ADMIN");
    }
}
