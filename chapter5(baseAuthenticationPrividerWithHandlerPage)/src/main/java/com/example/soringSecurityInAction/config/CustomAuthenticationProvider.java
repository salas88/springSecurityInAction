package com.example.soringSecurityInAction.config;

import com.example.soringSecurityInAction.entity.MyUser;
import com.example.soringSecurityInAction.service.InMemoryUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private InMemoryUserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        MyUser myUser = (MyUser) userDetailsService.loadUserByUsername(username);
        if(passwordEncoder.matches(password, myUser.getPassword())){
            return new UsernamePasswordAuthenticationToken(username,
                    password, myUser.getAuthorities());
        } else {
            throw new BadCredentialsException("Something was wrong");
        }
    }

    @Override
    public boolean supports(Class<?> authenticationType) {
        return authenticationType.equals(UsernamePasswordAuthenticationToken.class);
    }


}
