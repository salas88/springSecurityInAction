package com.example.smallWebSecurityApp.demo.config;

import com.example.smallWebSecurityApp.demo.entity.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.smallWebSecurityApp.demo.service.JpaUserDetailService;

@Service
public class AuthenticationProviderService implements AuthenticationProvider {
    @Autowired
    private  JpaUserDetailService userDetailService;
    @Autowired
    private  BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private  SCryptPasswordEncoder sCryptPasswordEncoder;



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        CustomUserDetail user = (CustomUserDetail) userDetailService.loadUserByUsername(username);

        switch (user.getUser().getAlgorithm()){
            case BCRYPT:
                return checkPassword(user, password, bCryptPasswordEncoder);
            case SCRYPT:
                return checkPassword(user, password, sCryptPasswordEncoder);
        }

        throw new BadCredentialsException("Bad credentials");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class
                .isAssignableFrom(aClass);
    }

    private Authentication checkPassword(CustomUserDetail customUserDetail,
                                         String rawPassword,
                                         PasswordEncoder passwordEncoder) {
        if(passwordEncoder.matches(rawPassword, customUserDetail.getPassword())){
            return new UsernamePasswordAuthenticationToken(
                    customUserDetail.getUsername(),
                    customUserDetail.getPassword(),
                    customUserDetail.getAuthorities());
        }else {
            throw new BadCredentialsException("Bad credentials");
        }

    }

}
