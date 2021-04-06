package com.example.smallWebSecurityApp.demo.service;

import com.example.smallWebSecurityApp.demo.entity.CustomUserDetail;
import com.example.smallWebSecurityApp.demo.entity.User;
import com.example.smallWebSecurityApp.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class JpaUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public JpaUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Supplier<UsernameNotFoundException> s =
                () -> new UsernameNotFoundException(
                        "Problem during authentication"
                );

        User user = userRepository.findUserByUsername(username)
                    .orElseThrow(s);

        return new CustomUserDetail(user);
    }
}
