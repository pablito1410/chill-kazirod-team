package com.chill.table.football.infrastructure.authentication;

import com.chill.table.football.infrastructure.authentication.exception.AuthMethodNotSupportedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {


    private final ObjectMapper objectMapper;

    public JwtLoginFilter(final String url,
                          final AuthenticationManager authManager,
                          final ObjectMapper objectMapper) {
        super(new AntPathRequestMatcher(url));
        this.objectMapper = objectMapper;
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException, ServletException {

        if (!HttpMethod.POST.name().equals(req.getMethod())) {
            throw new AuthMethodNotSupportedException("Authentication method not supported");
        }

        LoginRequestBody loginRequest = objectMapper.readValue(req.getReader(), LoginRequestBody.class);

        if (loginRequest.getUserName() == null || loginRequest.getUserName().isEmpty()
                || loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
            throw new AuthenticationServiceException("Username or Password not provided");
        }

        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUserName(),
                        loginRequest.getPassword(),
                        Collections.emptyList()));
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException, ServletException {
        TokenAuthenticationService.addAuthentication(res, auth.getName());
    }

}