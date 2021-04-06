package com.example.soringSecurityInAction.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    WRITE, READ;

    @Override
    public String getAuthority() {
        return name();
    }
}
