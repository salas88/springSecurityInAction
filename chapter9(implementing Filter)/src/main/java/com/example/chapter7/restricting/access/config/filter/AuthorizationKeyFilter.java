package com.example.chapter7.restricting.access.config.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Component
public class AuthorizationKeyFilter implements Filter {
    @Value("${authorization.key}")
    private String authorizationKey;

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        var req = (HttpServletRequest) servletRequest;
        var res = (HttpServletResponse) servletResponse;

        String authorization = req.getHeader("Authorization");

        if(authorization.equals(authorizationKey)){
            filterChain.doFilter(req,res);
        } else {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
