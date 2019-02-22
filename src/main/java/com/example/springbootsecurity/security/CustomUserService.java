package com.example.springbootsecurity.security;

import com.example.springbootsecurity.domain.Role;
import com.example.springbootsecurity.domain.User;
import com.example.springbootsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class CustomUserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + "用户信息不存在!");
        }

        List<GrantedAuthority> auths = new ArrayList<>();
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            auths.add(new SimpleGrantedAuthority(role.getRoleflag()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), auths);
    }
}