package com.example.soringSecurityInAction.service;

import com.example.soringSecurityInAction.dao.UserDao;
import com.example.soringSecurityInAction.entity.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class InMemoryUserDetailsService implements UserDetailsService {

    @Autowired
    public UserDao mImp;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return mImp.findByUsername(username);
    }
}
