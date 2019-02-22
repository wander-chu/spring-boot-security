package com.example.springbootsecurity.repository;

import com.example.springbootsecurity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    //使用Spring Data JPA方法定义查询
    User findByUsername(String username);
}