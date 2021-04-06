package com.example.soringSecurityInAction.dao;

import com.example.soringSecurityInAction.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserDao extends JpaRepository<MyUser, Long> {
    MyUser findByUsername(String username);
}
